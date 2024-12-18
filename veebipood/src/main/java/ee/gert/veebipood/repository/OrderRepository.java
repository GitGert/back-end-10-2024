package ee.gert.veebipood.repository;

import ee.gert.veebipood.entity.Order;
import ee.gert.veebipood.model.payment.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//import org.springframework.stereotype.Repository;
//
//@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByPerson_Email(String email);
}