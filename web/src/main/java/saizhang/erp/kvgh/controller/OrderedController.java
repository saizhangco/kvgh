package saizhang.erp.kvgh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import saizhang.erp.kvgh.entity.Order;
import saizhang.erp.kvgh.repo.OrderRepository;

import java.util.List;

/**
 * @Author : saizhang
 * @Date : 2019/09/15
 * @Time : 20:12
 * @Description : TODO
 */
@RestController
@RequestMapping("/ordered")
public class OrderedController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping()
    public List<Order> list() {
        List<Order> orderList = orderRepository.findAllByStatus(0);
        return orderList;
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable("id") long id) {
        Order order = orderRepository.findById(id).get();
        return order;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        Order order = orderRepository.findById(id).get();
        orderRepository.delete(order);
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable("id") Long id, Order order) {
        order.setId(id);
        orderRepository.save(order);
    }

}
