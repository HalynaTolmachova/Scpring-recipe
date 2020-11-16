package springrecipe.demo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import springrecipe.demo.commands.UnitOfMeasureCommand;
import springrecipe.demo.domain.UnitOfMeasure;
@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if(unitOfMeasure==null){
            return null;
        }else{
            UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
            unitOfMeasureCommand.setId(unitOfMeasure.getId());
            unitOfMeasureCommand.setDescription(unitOfMeasure.getDescription());
            return unitOfMeasureCommand;
        }
    }
}
