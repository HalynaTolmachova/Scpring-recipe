package springrecipe.demo.services;

import springrecipe.demo.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
