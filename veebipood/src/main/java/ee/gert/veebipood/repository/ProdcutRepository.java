package ee.gert.veebipood.repository;

import ee.gert.veebipood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Crudrepository is the basic one

//repository aka db

//This is hibernate - this will talk to the db
public interface ProdcutRepository extends JpaRepository<Product, Long> {


    List<Product> findByCategory_Id(Long id);


//    @Query("SELECT * FROM products WHERE category_id = :id")
//    List<Product> findAllProductsByCatebgoriyId(@Param("id") Long id);
}
