package ee.gert.veebipood;

import org.springframework.data.jpa.repository.JpaRepository;

//Crudrepository is the basic one


//This is hibernate - this will talk to the db
public interface ProdcutRepository extends JpaRepository<Product, Long> {



}
