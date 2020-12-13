package springrecipe.demo.services;

import springrecipe.demo.commands.IngredientCommand;
import springrecipe.demo.commands.RecipeCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);
    IngredientCommand saveIngredientCommand (IngredientCommand ingredientCommand);
    void deleteById(Long recipeId, Long id);
}
