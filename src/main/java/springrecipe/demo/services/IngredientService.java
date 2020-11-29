package springrecipe.demo.services;

import springrecipe.demo.commands.IngredientCommand;
import springrecipe.demo.commands.RecipeCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);

}
