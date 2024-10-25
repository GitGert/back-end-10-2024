package ee.gert.veebipood.repository;

import ee.gert.veebipood.entity.Address;
import ee.gert.veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String> {
    List<Person> findByAddress_StreetContainsIgnoreCase(String street);
}