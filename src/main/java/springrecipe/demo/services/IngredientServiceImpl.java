package springrecipe.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springrecipe.demo.commands.IngredientCommand;
import springrecipe.demo.commands.RecipeCommand;
import springrecipe.demo.converters.IngredientToIngredientCommand;
import springrecipe.demo.domain.Recipe;
import springrecipe.demo.repositories.RecipeRepository;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            log.debug("recipe id not found id: "+recipeId);
        }
        Recipe recipe = recipeOptional.get();

        Optional <IngredientCommand> ingredientCommand = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommand.isPresent()){
            log.debug("ingredient id not found id: "+ingredientId);
        }
        return ingredientCommand.get();
    }
}
