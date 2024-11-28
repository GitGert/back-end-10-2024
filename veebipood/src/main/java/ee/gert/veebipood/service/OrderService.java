package ee.gert.veebipood.service;

import ee.gert.veebipood.entity.Order;
import ee.gert.veebipood.entity.OrderRow;
import ee.gert.veebipood.entity.Product;
import ee.gert.veebipood.model.payment.*;
import ee.gert.veebipood.model.supplier.SupplierProductEscuela;
import ee.gert.veebipood.repository.OrderRepository;
import ee.gert.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class OrderService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrderRepository orderRepository;


    @Autowired
    ProductRepository productRepository;

    @Value("${everypay-url}")
    String everyPayUrl;


    @Value("${everypay-username}")
    String everyPayUsername;

    @Value("${everypay-authorization}")
    String everyPayAuthorization;



//    public OrderService(OrderRepository orderRepository,
//                        ProductRepository productRepository) {
//        this.orderRepository = orderRepository;
//        this.productRepository = productRepository;
//    }

    public Order saveOrder(Order order){
        order.setCreation(new Date());
        double totalSum =0;
        for(OrderRow orderRow : order.getOrderRows()){
            Product dbProduct = productRepository.findById(orderRow.getProduct().getId()).orElseThrow();
            totalSum += dbProduct.getPrice() * orderRow.getPcs();
        }
        order.setTotalSum(totalSum);
        orderRepository.save(order);
        return order;
    }

    public PaymentLink getPaymentLink(Order order) {

        String url = everyPayUrl + "/api/v4/payments/oneoff";
        System.out.println("orderID:");
        System.out.println(order.getId().toString());

        EveryPayBody body = new EveryPayBody();
        body.setAccount_name("EUR3D1");
        body.setNonce("234567defgyvbhf" + ZonedDateTime.now() + Math.random());
        body.setTimestamp(ZonedDateTime.now().toString());
        body.setAmount(order.getTotalSum());
        body.setOrder_reference(order.getId().toString());
        body.setCustomer_url("https://err.ee");
        body.setApi_username(everyPayUsername);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, everyPayAuthorization);
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpEntity<EveryPayBody> httpEntity = new HttpEntity<>(body,headers);

        ResponseEntity<EveryPayResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity, // <---requestEntity is body + headers
                EveryPayResponse.class
        );

//        ?order_reference=152515324
//        &payment_reference=e569ff1470d865a70dde5582027fa4f763f7cd92c53898de64fe4eb6a9436e59

        PaymentLink link = new PaymentLink();
        link.setLink(response.getBody().getPayment_link()); //FIXME: might be null
        return link;
    }

    public PaymentStatus checkPaymentStatus(String paymentReference) {
        String url = everyPayUrl + "/api/v4/payments/"
                +paymentReference +
                "?api_username=" + everyPayUsername+
                "&detailed=false";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "");
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpEntity<EveryPayPaymentStatusBody> httpEntity = new HttpEntity<>(null,headers);

        ResponseEntity<EveryPayPaymentStatusBody> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity, // <---requestEntity is body + headers
                EveryPayPaymentStatusBody.class
        );

        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setStatus(response.getBody().getPayment_state());
        return paymentStatus;
    }
}
