package saizhang.erp.kvgh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import saizhang.erp.kvgh.config.SystemEnv;
import saizhang.erp.kvgh.constant.PageConstant;
import saizhang.erp.kvgh.domain.ExcelFile;
import saizhang.erp.kvgh.domain.OrderEO;
import saizhang.erp.kvgh.entity.Excel;
import saizhang.erp.kvgh.entity.Order;
import saizhang.erp.kvgh.repo.ExcelRepository;
import saizhang.erp.kvgh.repo.OrderRepository;
import saizhang.erp.kvgh.utils.ExcelUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author : saizhang
 * @Date : 2019/10/06
 * @Time : 21:21
 * @Description : 订单管理
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelRepository excelRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SystemEnv systemEnv;

    @GetMapping("/{id}/file")
    public ExcelFile getExcelFile(@PathVariable("id") long id) {
        Excel excel = excelRepository.findById(id).get();
        List<Order> orderList = orderRepository.findAllByNo(excel.getOrderId());
        ExcelFile file = new ExcelFile();
        if (orderList == null || orderList.size() == 0) {
            file.setGenerate(false);
        } else {
            file.setPath(systemEnv.getExcelPath());
            file.setName(excel.getOrderId() + "-" + UUID.randomUUID().toString() + ".xls");
            List<OrderEO> orderEOList = new ArrayList<>();
            for (Order order : orderList) {
                orderEOList.add(new OrderEO(order));
            }
            try {
                file.setGenerate(true);
                ExcelUtil.createExcel(file.getPath() + file.getName(), orderEOList, OrderEO.class);
            } catch (Exception e) {
                e.printStackTrace();
                file.setGenerate(false);
            }
        }
        return file;
    }

    @GetMapping()
    public Page<Excel> list(@RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page, PageConstant.PAGE_SIZE, Sort.Direction.DESC, "id");
        Page<Excel> excelPage = excelRepository.findAll(pageable);
        return excelPage;
    }

    @GetMapping("/process/{processId}")
    public Page<Excel> listByProcessId(@RequestParam("page") int page, @PathVariable("processId") String processId) {
        Pageable pageable = PageRequest.of(page, PageConstant.PAGE_SIZE, Sort.Direction.DESC, "id");
        Page<Excel> excelPage = excelRepository.findByProcessId(processId, pageable);
        return excelPage;
    }

    @GetMapping("/status")
    public Page<Excel> listByStatus(@RequestParam("page") int page,
                                    @RequestParam("status") int status) {
        Pageable pageable = PageRequest.of(page, PageConstant.PAGE_SIZE, Sort.Direction.DESC, "id");
        Page<Excel> excelPage = excelRepository.findByStatus(status, pageable);
        return excelPage;
    }

    @GetMapping("/statusAndVendor")
    public Page<Excel> listByStatusAndVendor(@RequestParam("page") int page,
                                             @RequestParam("status") int status,
                                             @RequestParam("vendor") String vendor) {
        Pageable pageable = PageRequest.of(page, PageConstant.PAGE_SIZE, Sort.Direction.DESC, "id");
        Page<Excel> excelPage = excelRepository.findByStatusAndVendorLike(status, "%" + vendor + "%", pageable);
        return excelPage;
    }

    @GetMapping("/{id}")
    public Excel getById(@PathVariable("id") long id) {
        Excel excel = excelRepository.findById(id).get();
        return excel;
    }

    @PostMapping()
    public void add(@RequestBody Excel excel) {
        excelRepository.save(excel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        Excel excel = excelRepository.findById(id).get();
        excelRepository.delete(excel);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Excel excel) {
        excel.setId(id);
        excelRepository.save(excel);
    }
}
