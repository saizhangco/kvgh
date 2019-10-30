package com.daqiao.kvgh.controller;

import com.daqiao.kvgh.entity.HospitalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.daqiao.kvgh.constant.PageConstant;
import com.daqiao.kvgh.repo.HospitalInfoRepository;

/**
 * @Author : saizhang
 * @Date : 2019/09/15
 * @Time : 20:06
 * @Description : TODO
 */
@RestController
@RequestMapping("/hospitalInfo")
public class HospitalInfoController {

    @Autowired
    private HospitalInfoRepository hospitalInfoRepository;

    @GetMapping()
    public Page<HospitalInfo> list(@RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page, PageConstant.PAGE_SIZE, Sort.Direction.ASC, "id");
        Page<HospitalInfo> hospitalInfoPage = hospitalInfoRepository.findAll(pageable);
        return hospitalInfoPage;
    }

    @GetMapping("/{id}")
    public HospitalInfo getById(@PathVariable("id") long id) {
        HospitalInfo hospitalInfo = hospitalInfoRepository.findById(id).get();
        return hospitalInfo;
    }

    @PostMapping()
    public void add(@RequestBody HospitalInfo vendor) {
        hospitalInfoRepository.save(vendor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        HospitalInfo hospitalInfo = hospitalInfoRepository.findById(id).get();
        hospitalInfoRepository.delete(hospitalInfo);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id,@RequestBody HospitalInfo hospitalInfo) {
        hospitalInfo.setId(id);
        hospitalInfoRepository.save(hospitalInfo);
    }

}
