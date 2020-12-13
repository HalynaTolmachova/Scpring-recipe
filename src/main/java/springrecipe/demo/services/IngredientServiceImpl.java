package springrecipe.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springrecipe.demo.commands.IngredientCommand;
import springrecipe.demo.converters.IngredientCommandToIngredient;
import springrecipe.demo.converters.IngredientToIngredientCommand;
import springrecipe.demo.domain.Ingredient;
import springrecipe.demo.domain.Recipe;
import springrecipe.demo.repositories.RecipeRepository;
import springrecipe.demo.repositories.UnitOfMeasureRepository;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;

        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
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

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
       Optional<Recipe>optionalRecipe = recipeRepository.findById(ingredientCommand.getRecipeId());
       if(!optionalRecipe.isPresent()){
           log.debug("Recipe is not present " +ingredientCommand.getRecipeId());
           return  new IngredientCommand();
       }else{
            Recipe recipe = optionalRecipe.get();
           Optional <Ingredient> optionalIngredient = recipe
                   .getIngredients()
                   .stream()
                   .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                   .findFirst();
           if(optionalIngredient.isPresent()){
               Ingredient ingredientFound = optionalIngredient.get();
               ingredientFound.setDescription(ingredientCommand.getDescription());
               ingredientFound.setAmount(ingredientCommand.getAmount());
               ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                       .findById(ingredientCommand.getUnitOfMeasure().getId())
                       .orElseThrow(()->new RuntimeException("Unit of Measure not founf")));
           }else {
               Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
               ingredient.setRecipe(recipe);
               recipe.addIngredients(ingredient);
           }
            Recipe savedRecipe = recipeRepository.save(recipe);
            Optional<Ingredient> savedIngredientOptinal =savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients->recipeIngredients.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            if(!savedIngredientOptinal.isPresent()){
                savedIngredientOptinal = savedRecipe.getIngredients().stream()
                .filter(recipeIngridient -> recipeIngridient.getDescription().equals(ingredientCommand.getDescription()))
                        .filter((recipeIngridient-> recipeIngridient.getAmount().equals(ingredientCommand.getAmount())))
                        .filter(recipeIngredient->recipeIngredient.getUnitOfMeasure().getId().equals(ingredientCommand.getUnitOfMeasure().getId()))
                        .findFirst();

            }


        return ingredientToIngredientCommand.convert(savedIngredientOptinal.get());
      }
}

    @Override
    public void deleteById(Long recipeId, Long id) {
        log.debug("Deleting ingredient: " + recipeId + ":" + id);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();
            log.debug("found recipe");
            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(id))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                log.debug("found Ingredient");
                Ingredient ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe);
            }
        }else{
            log.debug("Recipe Id Not found. Id:" + recipeId);
        }

    }
}
