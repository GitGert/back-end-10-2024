package ee.gert.veebipood.service;

import ee.gert.veebipood.model.supplier.SupplierProduct;
import ee.gert.veebipood.model.supplier.SupplierProductEscuela;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
public class SupplierService {

    @Autowired
    RestTemplate restTemplate;

    public List<SupplierProduct> getProducts(){

//        RestTemplate restTemplate =  new RestTemplate(); // niimodi luua ei tohiks!
        System.out.println("login välja");
        log.info("login välja");
        log.info(restTemplate);

        String url = "https://fakestoreapi.com/products";

        ResponseEntity<SupplierProduct[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null, // <---requestEntity is body + headers
                SupplierProduct[].class );

        if (response.getBody() == null){
            return new ArrayList<>();
        }
//        return List.of(response.getBody());
        return Arrays.asList(response.getBody());
    }

    public List<SupplierProductEscuela> getProductsEscuela() {
//        RestTemplate restTemplate =  new RestTemplate();

        String url = "https://api.escuelajs.co/api/v1/products";

        ResponseEntity<SupplierProductEscuela[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null, // <---requestEntity is body + headers
                SupplierProductEscuela[].class );

        if (response.getBody() == null){
            return new ArrayList<>();
        }
        return Arrays.asList(response.getBody());
    }
}
