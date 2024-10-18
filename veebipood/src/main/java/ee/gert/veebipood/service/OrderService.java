package ee.gert.veebipood.service;

import ee.gert.veebipood.entity.Order;
import ee.gert.veebipood.entity.OrderRow;
import ee.gert.veebipood.entity.Product;
import ee.gert.veebipood.repository.OrderRepository;
import ee.gert.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public void saveOrder(Order order){
        order.setCreation(new Date());
        double totalSum =0;
        for(OrderRow orderRow : order.getOrderRows()){
            Product dbProduct = productRepository.findById(orderRow.getProduct().getId()).orElseThrow();
            totalSum += dbProduct.getPrice() * orderRow.getPcs();
        }
        order.setTotalSum(totalSum);
        orderRepository.save(order);
    }
}
