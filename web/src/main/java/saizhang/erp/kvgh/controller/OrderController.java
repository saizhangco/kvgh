package saizhang.erp.kvgh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import saizhang.erp.kvgh.constant.PageConstant;
import saizhang.erp.kvgh.entity.Order;
import saizhang.erp.kvgh.repo.OrderRepository;
import saizhang.erp.kvgh.utils.DateUtil;

import java.util.List;

/**
 * @Author : saizhang
 * @Date : 2019/09/15
 * @Time : 20:15
 * @Description : TODO
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/ordering")
    public List<Order> orderingList() {
        List<Order> orderList = orderRepository.findAllByStatus(0);
        return orderList;
    }

    @GetMapping("/ordered")
    public List<Order> orderedList() {
        List<Order> orderList = orderRepository.findAllByStatus(1);
        return orderList;
    }

    @GetMapping("/accepted")
    public List<Order> acceptedList() {
        List<Order> orderList = orderRepository.findAllByStatus(2);
        return orderList;
    }

    @GetMapping("/no/{no}")
    public List<Order> listByNo(@PathVariable("no") String no) {
        List<Order> orderList = orderRepository.findAllByNoLike(no + "%");
        return orderList;
    }

    @GetMapping()
    public Page<Order> list(@RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page, PageConstant.PAGE_SIZE, Sort.Direction.DESC, "id");
        Page<Order> orderPage = orderRepository.findAll(pageable);
        return orderPage;
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable("id") long id) {
        Order order = orderRepository.findById(id).get();
        return order;
    }

    @PostMapping()
    public void add(Order order) {
        order.setDate(DateUtil.getDateString());
        orderRepository.save(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        Order order = orderRepository.findById(id).get();
        orderRepository.delete(order);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, Order order) {
        order.setId(id);
        orderRepository.save(order);
    }

}
