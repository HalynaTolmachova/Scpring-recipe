package springrecipe.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import springrecipe.demo.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>{
    Optional<Category>findByDescription(String description);

}
