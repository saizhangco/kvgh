package saizhang.erp.kvgh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import saizhang.erp.kvgh.constant.PageConstant;
import saizhang.erp.kvgh.entity.User;
import saizhang.erp.kvgh.repo.UserRepository;

import java.util.List;

/**
 * @Author : saizhang
 * @Date : 2019/09/28
 * @Time : 10:29
 * @Description : TODO
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public Page<User> list(@RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page, PageConstant.PAGE_SIZE, Sort.Direction.ASC, "id");
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @GetMapping("/name/{name}")
    public User getByName(@PathVariable("name") String name) {
        return userRepository.findByUsername(name);
    }

    @PostMapping()
    public void add(@RequestBody User user) {
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        userRepository.save(user);
    }
}
