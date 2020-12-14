package springrecipe.demo.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import springrecipe.demo.domain.Difficulty;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;
    @Min(1)
    @Max(999)
    private Integer prepTime;
    @Min(1)
    @Max(999)
    private Integer cookTime;
    @Min(1)
    @Max(1000)
    private Integer serving;
    private String source;
    @URL
    private String Url;
    @NotBlank
    private String directions;
    private Byte[] image;
    private Difficulty difficulty;
    private Set<IngredientCommand> ingredients=new HashSet<>();
    private NoteCommand notes;
    private Set<CategoryCommand>categories = new HashSet<>();



}
