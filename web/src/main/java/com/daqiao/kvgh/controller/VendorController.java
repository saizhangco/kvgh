package com.daqiao.kvgh.controller;

import com.daqiao.kvgh.constant.PageConstant;
import com.daqiao.kvgh.repo.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.daqiao.kvgh.entity.Vendor;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 17:33
 * @Description : TODO
 */
@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private VendorRepository vendorRepository;

    @GetMapping()
    public Page<Vendor> list(@RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page, PageConstant.PAGE_SIZE, Sort.Direction.ASC, "code");
        Page<Vendor> vendorPage = vendorRepository.findAll(pageable);
        return vendorPage;
    }

    @GetMapping("/name")
    public Vendor getByName(@RequestParam("name") String name) {
        return vendorRepository.getVendorByName(name);
    }

    @GetMapping("/{id}")
    public Vendor getById(@PathVariable("id") long id) {
        Vendor vendor = vendorRepository.findById(id).get();
        return vendor;
    }

    @PostMapping()
    public void add(@RequestBody Vendor vendor) {
        vendorRepository.save(vendor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        Vendor vendor = vendorRepository.findById(id).get();
        vendorRepository.delete(vendor);
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable("id") Long id, @RequestBody Vendor vendor) {
        vendor.setId(id);
        vendorRepository.save(vendor);
    }

}
