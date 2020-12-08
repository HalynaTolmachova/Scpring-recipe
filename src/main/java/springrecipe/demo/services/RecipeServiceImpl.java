package springrecipe.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springrecipe.demo.commands.RecipeCommand;
import springrecipe.demo.converters.RecipeCommandToRecipe;
import springrecipe.demo.converters.RecipeToRecipeCommand;
import springrecipe.demo.domain.Recipe;
import springrecipe.demo.exceptions.NotFoundException;
import springrecipe.demo.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        log.debug("im in the service");
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id){
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(!recipe.isPresent()){
           // throw new RuntimeException("Recipe is not in the list");
            throw new NotFoundException("Recipe is not in the list. For ID value: " + id.toString());
        }else{
            return recipe.get();
        }
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved recipe "+ savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);

    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
