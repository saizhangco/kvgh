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
import saizhang.erp.kvgh.domain.Stock;
import saizhang.erp.kvgh.entity.Acceptance;
import saizhang.erp.kvgh.entity.Excel;
import saizhang.erp.kvgh.entity.Order;
import saizhang.erp.kvgh.entity.User;
import saizhang.erp.kvgh.repo.AcceptanceRepository;
import saizhang.erp.kvgh.repo.ExcelRepository;
import saizhang.erp.kvgh.repo.OrderRepository;
import saizhang.erp.kvgh.repo.UserRepository;
import saizhang.erp.kvgh.utils.ExcelUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author : saizhang
 * @Date : 2019/09/15
 * @Time : 11:16
 * @Description : 验收流程
 */
@RestController
@RequestMapping("/acceptance")
public class AcceptanceController {

    @Autowired
    private AcceptanceRepository acceptanceRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ExcelRepository excelRepository;

    @Autowired
    private SystemEnv systemEnv;

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public Page<Acceptance> list(@RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page, PageConstant.PAGE_SIZE, Sort.Direction.DESC, "id");
        Page<Acceptance> acceptancePage = acceptanceRepository.findAll(pageable);
        return acceptancePage;
    }

    @GetMapping("/{id}")
    public Acceptance getById(@PathVariable("id") long id) {
        Acceptance acceptance = acceptanceRepository.findById(id).get();
        return acceptance;
    }

    @GetMapping("/order/{id}")
    public Acceptance getByOrderId(@PathVariable("id") long id) {
        Acceptance acceptance = acceptanceRepository.findByOrderId(id);
        return acceptance;
    }

    /**
     * 验收流程
     * @param acceptance
     */
    @PostMapping()
    public void add(@RequestBody Acceptance acceptance) {
        Order order = orderRepository.findById(acceptance.getOrderId()).get();
        if( order == null ) {
            throw new RuntimeException("order is null!");
        }
        if( Integer.parseInt(acceptance.getNumber()) <= 0 ) {
            return;
        }
//        if( order.getNumber() > Integer.parseInt(acceptance.getNumber()) ) {
//            order.setStatus(1);
//            orderRepository.save(order);
//            acceptanceRepository.save(acceptance);
//            order.setId(null);
//            order.setStatus(0);
//            order.setNumber(order.getNumber() - Integer.parseInt(acceptance.getNumber()));
//            order.setNo(order.getNo() + "p");
//            orderRepository.save(order);
//        } else if (order.getNumber() == Integer.parseInt(acceptance.getNumber())) {
        else {
            order.setStatus(1);
            orderRepository.save(order);
            acceptanceRepository.save(acceptance);
            if( orderRepository.countByNoLikeAndStatus(noWithoutP(order.getNo()) + "%", 0) == 0 ) {
                Excel excel = excelRepository.findByOrderId(noWithoutP(order.getNo()));
                excel.setStatus(1);
                excelRepository.save(excel);
            }
        }
    }

    private String noWithoutP(String no) {
        String result = no;
        while(result.endsWith("p")){
            result = result.substring(0, result.length()-1);
        }
        return result;
    }

    @PostMapping("/file")
    public ExcelFile generateExcelFile(@RequestBody List<Long> idList) {
        ExcelFile excelFile = new ExcelFile();
        List<Stock> stockList = new ArrayList<>();

        for( Long id : idList) {
            Excel excel = excelRepository.findById(id).get();
            List<Order> orders = orderRepository.findAllByNoLike(excel.getOrderId() + "%");
            for( Order order : orders ) {
                Stock stock = new Stock();
                Acceptance acceptance = acceptanceRepository.findByOrderId(order.getId());
                User purchaser = userRepository.findByUsername(order.getApplicant());
                User orderer = userRepository.findByUsername(order.getOrderer());
                User accepter = userRepository.findByUsername(acceptance.getAccepter());
                stock.setHospitalId(order.getHospitalCode());
                stock.setOrderNo(order.getNo());
                stock.setOrderDate(order.getDate());
                stock.setOrderType(order.getType());
                stock.setOriginOrderNo(order.getOriginNo());
                stock.setPurchaseNo(order.getPurchaseNo());
                stock.setPurchaseDepartment(order.getApplicantDepartment());
                stock.setPurchaser(order.getApplicant());
                stock.setPurchaserPhone(purchaser.getPhone());
                stock.setPurchaserFax(purchaser.getFax());
                stock.setPurchaserEmail(purchaser.getEmail());
                stock.setOrderer(order.getOrderer());
                stock.setOrdererPhone(orderer.getPhone());
                stock.setOrdererFax(orderer.getFax());
                stock.setOrdererEmail(orderer.getEmail());
                stock.setDeliveryAddress(order.getDeliveryAddress());
                stock.setHint(order.getHint());
                stock.setOrderAmount(order.getAmount().toString());
                stock.setInternationalBarCode("");
                stock.setUnit(order.getUnit());
                stock.setUnitPrice(order.getUnitPrice().toString());
                stock.setOrderNumber(order.getNumber().toString());
                stock.setAcceptanceDate(acceptance.getDate());
                stock.setAccepter(acceptance.getAccepter());
                stock.setAccepterPhone(accepter.getPhone());
                stock.setAccepterFax(accepter.getFax());
                stock.setAccepterEmail(accepter.getEmail());
                stock.setAcceptanceTimes(acceptance.getTimes().toString());
                stock.setAcceptanceNumber(acceptance.getNumber());
                stock.setPeriodOfValidity(acceptance.getPeriodOfValidity());
                stock.setBatchNumber(acceptance.getBatchNumber());
                stock.setVendorCode(acceptance.getVendorCode());
                stock.setInvoiceNo(acceptance.getInvoiceNo());
                stock.setApplicationAmount(acceptance.getAmount().toString());
                stock.setMaterialDiscount(acceptance.getMaterialDiscount().toString());
                stock.setMedicinalDiscount(acceptance.getMedicinalDiscount().toString());
                stock.setDescription(acceptance.getDescription());
                stockList.add(stock);
            }
        }

        excelFile.setPath(systemEnv.getExcelPath());
        excelFile.setName(UUID.randomUUID().toString() + ".xls");
        try {
            excelFile.setGenerate(true);
            ExcelUtil.createExcel(excelFile.getPath() + excelFile.getName(), stockList, Stock.class);
        } catch (Exception e) {
            e.printStackTrace();
            excelFile.setGenerate(false);
        }
        return excelFile;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        Acceptance acceptance = acceptanceRepository.findById(id).get();
        acceptanceRepository.delete(acceptance);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Acceptance acceptance) {
        acceptance.setId(id);
        acceptanceRepository.save(acceptance);
    }

}
