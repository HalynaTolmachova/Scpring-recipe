package springrecipe.demo.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import springrecipe.demo.commands.UnitOfMeasureCommand;
import springrecipe.demo.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
    @Nullable
    @Synchronized
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if(unitOfMeasureCommand ==null){
            return null;
        }else{
         UnitOfMeasure unitOfMeasure =new UnitOfMeasure();
         unitOfMeasure.setId(unitOfMeasureCommand.getId());
         unitOfMeasure.setDescription((unitOfMeasureCommand.getDescription()));
            return unitOfMeasure;
        }
    }
}
