package springrecipe.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import springrecipe.demo.domain.Recipe;
import springrecipe.demo.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceImplTest {
    RecipeServiceImpl recipeServiceImpl;
    @Mock
    RecipeRepository recipeRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {
    Recipe recipe = new Recipe();
    Set<Recipe> recipeData = new HashSet<>();
    recipeData.add(recipe);

    Mockito.when(recipeRepository.findAll()).thenReturn(recipeData);
    Set<Recipe> recipes = recipeServiceImpl.getRecipes();
    assertEquals(recipes.size(),1);
    }
}