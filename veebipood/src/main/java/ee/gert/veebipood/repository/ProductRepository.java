package ee.gert.veebipood.repository;

import ee.gert.veebipood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}