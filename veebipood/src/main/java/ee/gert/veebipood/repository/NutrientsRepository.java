package ee.gert.veebipood.repository;

import ee.gert.veebipood.entity.Nutrients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientsRepository extends JpaRepository<Nutrients, Long> {
}
