package springrecipe.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import springrecipe.demo.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure>findByDescription(String description);
}
