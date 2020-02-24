package com.daqiao.kvgh.controller;

import com.daqiao.kvgh.config.SystemEnv;
import com.daqiao.kvgh.constant.PageConstant;
import com.daqiao.kvgh.domain.ExcelFile;
import com.daqiao.kvgh.domain.OrderEO;
import com.daqiao.kvgh.entity.Order;
import com.daqiao.kvgh.repo.ExcelRepository;
import com.daqiao.kvgh.repo.OrderRepository;
import com.daqiao.kvgh.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.daqiao.kvgh.entity.Excel;

import java.util.ArrayList;
import java.util.HashSet;
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

    @GetMapping("/hospitalCode")
    public List<Excel> listByHospitalCode(@RequestParam("hospitalCode") String hospitalCode) {
        HashSet<String> orderNoList = new HashSet<>();
        List<Order> orderList = orderRepository.findFirst15ByStatusAndHospitalCode(0, hospitalCode);
        for (Order order : orderList) {
            orderNoList.add(order.getNo());
        }
        List<Excel> excelList = new ArrayList<>();
        for (String orderNo : orderNoList) {
            Excel excel = excelRepository.findByOrderId(orderNo);
            if( excel != null ) {
                excelList.add(excel);
            }
        }
        return excelList;
    }

    @GetMapping("/vendorBarCode")
    public List<Excel> listByVendorBarCode(@RequestParam("vendorBarCode") String vendorBarCode) {
        return null;
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
