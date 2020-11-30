package springrecipe.demo.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springrecipe.demo.domain.Difficulty;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    private String source;
    private String Url;
    private String directions;
    private Byte[] image;
    private Difficulty difficulty;
    private Set<IngredientCommand> ingredients=new HashSet<>();
    private NoteCommand notes;
    private Set<CategoryCommand>categories = new HashSet<>();



}
