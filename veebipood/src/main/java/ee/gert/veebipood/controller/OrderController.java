package ee.gert.veebipood.controller;


import ee.gert.veebipood.entity.Order;
import ee.gert.veebipood.entity.Person;
import ee.gert.veebipood.repository.OrderRepository;
import ee.gert.veebipood.repository.PersonRepository;
import ee.gert.veebipood.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
        // get the email from login token not from order that the user sends to BE.
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Person person = new Person();
        person.setEmail(email);
        order.setPerson(person); // lisame personile ainult emaili sest me ei vaja tervet personit DB-st

        orderService.saveOrder(order);
        return orderRepository.findByPerson_Email(email);
    }
}
