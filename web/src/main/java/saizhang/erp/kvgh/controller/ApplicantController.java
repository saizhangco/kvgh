package saizhang.erp.kvgh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.bind.annotation.*;
import saizhang.erp.kvgh.entity.Excel;
import saizhang.erp.kvgh.entity.NumberStorage;
import saizhang.erp.kvgh.entity.Order;
import saizhang.erp.kvgh.entity.Vendor;
import saizhang.erp.kvgh.repo.ExcelRepository;
import saizhang.erp.kvgh.repo.NumberStorageRepository;
import saizhang.erp.kvgh.repo.OrderRepository;
import saizhang.erp.kvgh.repo.VendorRepository;
import saizhang.erp.kvgh.utils.CountUtil;
import saizhang.erp.kvgh.utils.DateUtil;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : saizhang
 * @Date : 2019/09/15
 * @Time : 11:29
 * @Description : 请购流程，完成时会生成订单
 */
@RestController
@RequestMapping("/applicant")
public class ApplicantController {

    private Map<String, List<Order>> applicantOrderMap = new ConcurrentHashMap<>();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ExcelRepository excelRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private NumberStorageRepository numberStorageRepository;

    //@Autowired
    private DataSourceTransactionManager transactionManager;

    @GetMapping()
    public List<Order> list(@NotNull @RequestParam("session") String session) {
        List<Order> list = applicantOrderMap.get(session);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @GetMapping("/{id}")
    public Order getById(@NotNull @RequestParam("session") String session,
                         @PathVariable("id") String id) {
        Order order = null;
        List<Order> list = applicantOrderMap.get(session);
        if (list == null) {
            return order;
        }
        for (Order o : list) {
            if (o.getNo().equals(id)) {
                order = o;
                break;
            }
        }
        return order;
    }

    @PostMapping()
    public void add(@RequestBody Order order,
                    @NotNull @RequestParam("session") String session) {
        List<Order> list = applicantOrderMap.get(session);
        if (list == null) {
            list = new ArrayList<Order>();
        }
        list.add(order);
        applicantOrderMap.put(session, list);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id,
                       @NotNull @RequestParam("session") String session) {
        List<Order> list = applicantOrderMap.get(session);
        if (list != null) {
            for (Order o : list) {
                if (o.getNo().equals(id)) {
                    list.remove(o);
                    break;
                }
            }
        }
        applicantOrderMap.put(session, list);
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable("id") String id,
                     @NotNull @RequestParam("session") String session,
                     @RequestBody Order order) {
        List<Order> list = applicantOrderMap.get(session);
        if (list != null) {
            for (Order o : list) {
                if (o.getNo().equals(id)) {
                    list.remove(o);
                    list.add(order);
                }
            }
        } else {
            list = new ArrayList<>();
            list.add(order);
        }
        applicantOrderMap.put(session, list);
    }

    @PostMapping("/complete")
//    @Transactional
    public Excel complete(@NotNull @RequestParam("session") String session) {
//        DefaultTransactionDefinition dtf = new DefaultTransactionDefinition();
//        dtf.setName("applicantOrderComplete");
//        dtf.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//
//        TransactionStatus status = transactionManager.getTransaction(dtf);
        String processId = UUID.randomUUID().toString();
        try {
            List<Order> list = applicantOrderMap.get(session);
            Map<String, Integer> vendorMap = new HashMap<>(16);
            Map<String, String> orderIdMap = new HashMap<>(16);
            if (list != null) {
                for (Order order : list) {
                    Integer size = vendorMap.get(order.getVendor()) == null ? 0 : vendorMap.get(order.getVendor());
                    vendorMap.put(order.getVendor(), size + 1);
                }
            }
            //给订单做编号
            int count = 0;
            NumberStorage numberStorage = numberStorageRepository.findByDate(DateUtil.getDateString());
            if (numberStorage == null) {
                numberStorage = new NumberStorage();
                numberStorage.setCount(1);
                numberStorage.setDate(DateUtil.getDateString());
                numberStorageRepository.save(numberStorage);
            } else {
                count = numberStorage.getCount();
                numberStorage.setCount(count + 1);
                numberStorageRepository.save(numberStorage);
            }
            for (Map.Entry entry : vendorMap.entrySet()) {
                Excel excel = new Excel();
                excel.setProcessId(processId);
                excel.setVendor(entry.getKey().toString());
                Vendor vendor = vendorRepository.getVendorByName(entry.getKey().toString());
                if (vendor != null) {
                    excel.setVendorEmail(vendor.getEmail());
                }
                count++;
                excel.setOrderId(DateUtil.getDateString() + CountUtil.getCount(new Long(count)));
                excel.setOrderSize(Integer.parseInt(entry.getValue().toString()));
                excel.setDate(DateUtil.getDateString());
                excel.setExcelName(excel.getOrderId() + "-" + excel.getProcessId() + ".xls");
                //标记为未验收
                excel.setStatus(0);
                excelRepository.save(excel);
                orderIdMap.put(excel.getVendor(), excel.getOrderId());
            }
            if (list != null) {
                for (Order order : list) {
                    String orderId = orderIdMap.get(order.getVendor());
                    if (orderId == null) {
                        throw new RuntimeException("OrderId不能为空");
                    }
                    order.setNo(orderId);
                    order.setDate(DateUtil.getDateString());
                    orderRepository.save(order);
                }
            }
            // 更新count计数器
            numberStorage = numberStorageRepository.findByDate(DateUtil.getDateString());
            if (numberStorage == null) {
                numberStorage = new NumberStorage();
                numberStorage.setCount(count);
                numberStorage.setDate(DateUtil.getDateString());
                numberStorageRepository.save(numberStorage);
            }
            else {
                numberStorage.setCount(count);
                numberStorageRepository.save(numberStorage);
            }
            // end 更新count计数器
        } catch (Exception e) {
//            transactionManager.rollback(status);
            throw e;
        }
        applicantOrderMap.remove(session);
        Excel excel = new Excel();
        excel.setProcessId(processId);
        excel.setId(0L);
        excel.setOrderSize(0);
        excel.setStatus(0);
        return excel;
    }
}
