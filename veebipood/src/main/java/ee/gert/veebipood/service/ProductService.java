package ee.gert.veebipood.service;

import ee.gert.veebipood.entity.Product;
import ee.gert.veebipood.exception.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public void validateProduct(Product prodcut) throws ValidationException {
        if (prodcut.getName() == null || prodcut.getName().isBlank()) {
            throw new ValidationException("Product name cannot be empty");
//            throw new Exception("product name cannot be empty");
        }
    }
}
