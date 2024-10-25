package ee.gert.veebipood.controller;


import ee.gert.veebipood.entity.Order;
import ee.gert.veebipood.repository.OrderRepository;
import ee.gert.veebipood.repository.PersonRepository;
import ee.gert.veebipood.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;


    @Autowired
    OrderService orderService;

    @GetMapping("order/{id}")
    public Order getOrderByid(@PathVariable Long id){
        return orderRepository.findById(id).orElseThrow();
    }

    @PostMapping("order")
    @Transactional // salvestatakse ka ära tesised väärtused DB-s
    public List<Order> saveOrder(@RequestBody Order order){
        orderService.saveOrder(order);
        return orderRepository.findByPerson_Email(order.getPerson().getEmail());
    }
}
