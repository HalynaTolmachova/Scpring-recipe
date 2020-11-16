package springrecipe.demo.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import springrecipe.demo.commands.IngredientCommand;
import springrecipe.demo.domain.Ingredient;
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient==null){
            return null;
        }else{
            IngredientCommand ingredientCommand=new IngredientCommand();
            ingredientCommand.setId(ingredient.getId());
            ingredientCommand.setDescription(ingredient.getDescription());
            ingredientCommand.setAmount(ingredient.getAmount());
            ingredientCommand.setUnitOfMeasure(ingredient.getUnitOfMeasure());
            return ingredientCommand;
        }
    }
}