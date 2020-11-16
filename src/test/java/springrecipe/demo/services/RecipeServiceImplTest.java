package springrecipe.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springrecipe.demo.converters.RecipeCommandToRecipe;
import springrecipe.demo.converters.RecipeToRecipeCommand;
import springrecipe.demo.domain.Recipe;
import springrecipe.demo.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {
    RecipeService recipeService;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void getRecipes() {
    Recipe recipe = new Recipe();
    Set<Recipe> recipeData = new HashSet<>();
    recipeData.add(recipe);

    when(recipeRepository.findAll()).thenReturn(recipeData);
    Set<Recipe> recipes = recipeService.getRecipes();
    assertEquals(recipes.size(),1);
    }
    @Test
    void getRecipesById(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Recipe recipeReturned = recipeService.findById(1L);
        assertEquals(1L, recipeReturned.getId());
        assertNotNull(recipeReturned);
        verify(recipeRepository,times(1)).findById(anyLong());



    }
}