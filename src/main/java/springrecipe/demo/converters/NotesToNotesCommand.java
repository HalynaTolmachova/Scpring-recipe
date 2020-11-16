package springrecipe.demo.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import springrecipe.demo.commands.NoteCommand;
import springrecipe.demo.domain.Notes;

@Component
public class NotesToNotesCommand implements Converter<Notes, NoteCommand> {

    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Notes notes) {
        if (notes == null) {
            return null;
        }

        final NoteCommand notesCommand = new NoteCommand();
        notesCommand.setId(notes.getId());
        notesCommand.setRecipeNotes(notes.getRecipeNotes());
        return notesCommand;
    }
}
