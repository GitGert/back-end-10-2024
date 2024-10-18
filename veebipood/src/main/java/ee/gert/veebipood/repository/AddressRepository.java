package ee.gert.veebipood.repository;

import ee.gert.veebipood.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}