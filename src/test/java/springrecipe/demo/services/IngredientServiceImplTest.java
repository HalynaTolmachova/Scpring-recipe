package springrecipe.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springrecipe.demo.commands.IngredientCommand;
import springrecipe.demo.converters.IngredientCommandToIngredient;
import springrecipe.demo.converters.IngredientToIngredientCommand;
import springrecipe.demo.converters.UnitOfMeasureToUnitOfMeasureCommand;
import springrecipe.demo.domain.Ingredient;
import springrecipe.demo.domain.Recipe;
import springrecipe.demo.repositories.RecipeRepository;
import springrecipe.demo.repositories.UnitOfMeasureRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;
    IngredientCommandToIngredient ingredientCommandToIngredient;
    UnitOfMeasureRepository unitOfMeasureRepository;
    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl( ingredientToIngredientCommand, recipeRepository, unitOfMeasureRepository,ingredientCommandToIngredient);
    }

    @Test
    void findByRecipeIdAndIngredientId() {

        Recipe recipe =new Recipe();
        recipe.setId(1L);
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredients(ingredient1);
        recipe.addIngredients(ingredient2);
        recipe.addIngredients(ingredient3);
        Optional <Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L,3L);

        assertEquals(3,ingredientCommand.getId());
        assertEquals(1, ingredientCommand.getRecipeId());
        verify(recipeRepository,times(1)).findById(anyLong());


    }
}