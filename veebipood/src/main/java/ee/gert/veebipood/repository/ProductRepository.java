package ee.gert.veebipood.repository;

import ee.gert.veebipood.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository

//Crudrepository is the basic one

//repository aka db

//This is hibernate - this will talk to the db
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByCategory_Id(Long id);

    Page<Product> findByNameContainsIgnoreCase(String name, Pageable pageable);

    List<Product> findAllByOrderByIdAsc();
//    @Query("SELECT * FROM products WHERE category_id = :id")
//    List<Product> findAllProductsByCatebgoriyId(@Param("id") Long id);

}
