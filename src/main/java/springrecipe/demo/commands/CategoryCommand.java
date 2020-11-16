package springrecipe.demo.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springrecipe.demo.domain.Recipe;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;
}
