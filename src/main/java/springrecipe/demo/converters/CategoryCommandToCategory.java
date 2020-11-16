package springrecipe.demo.converters;


import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import springrecipe.demo.commands.CategoryCommand;
import springrecipe.demo.domain.Category;
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand ==null){
            return null;
        }else{
            Category category = new Category();
            category.setId(categoryCommand.getId());
            category.setDescription(categoryCommand.getDescription());
            return category;
        }

    }
}
