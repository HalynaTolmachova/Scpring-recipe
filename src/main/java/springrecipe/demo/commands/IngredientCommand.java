package springrecipe.demo.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springrecipe.demo.domain.UnitOfMeasure;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasure unitOfMeasure;

}
