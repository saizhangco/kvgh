package saizhang.erp.kvgh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import saizhang.erp.kvgh.constant.PageConstant;
import saizhang.erp.kvgh.entity.Medicine;
import saizhang.erp.kvgh.repo.MedicineRepository;

import java.util.List;

/**
 * @Author : saizhang
 * @Date : 2019/09/15
 * @Time : 20:09
 * @Description : TODO
 */
@RestController
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping()
    public Page<Medicine> list(@RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page, PageConstant.PAGE_SIZE, Sort.Direction.ASC, "hospitalNo");
        Page<Medicine> medicinePage = medicineRepository.findAll(pageable);
        return medicinePage;
    }

    @GetMapping("/byName")
    public Page<Medicine> listByName(@RequestParam("name") String name) {
        Pageable pageable = PageRequest.of(0, PageConstant.PAGE_SIZE, Sort.Direction.ASC, "name");
        Page<Medicine> medicinePage = medicineRepository.findByNameLike("%" + name + "%", pageable);
        return medicinePage;
    }

    @GetMapping("/name")
    public Medicine getByName(@RequestParam("name") String name) {
        return medicineRepository.findByName(name);
    }

    @GetMapping("/{id}")
    public Medicine getById(@PathVariable("id") long id) {
        Medicine medicine = medicineRepository.findById(id).get();
        return medicine;
    }

    @PostMapping()
    public void add(@RequestBody  Medicine medicine) {
        medicineRepository.save(medicine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        Medicine medicine = medicineRepository.findById(id).get();
        medicineRepository.delete(medicine);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Medicine medicine) {
        medicine.setId(id);
        medicineRepository.save(medicine);
    }

}
