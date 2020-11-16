package springrecipe.demo.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import springrecipe.demo.commands.IngredientCommand;
import springrecipe.demo.domain.Ingredient;
@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand==null){
            return null;
        }else{
            Ingredient ingredient=new Ingredient();
            ingredient.setId(ingredientCommand.getId());
            ingredient.setDescription(ingredientCommand.getDescription());
            ingredient.setAmount(ingredientCommand.getAmount());
            ingredient.setUnitOfMeasure(ingredientCommand.getUnitOfMeasure());
            return ingredient;
        }
    }
}