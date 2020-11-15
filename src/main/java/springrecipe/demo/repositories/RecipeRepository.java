package springrecipe.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import springrecipe.demo.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
