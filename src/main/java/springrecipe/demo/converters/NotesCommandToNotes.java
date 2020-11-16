package springrecipe.demo.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import springrecipe.demo.commands.NoteCommand;
import springrecipe.demo.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NoteCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NoteCommand noteCommand) {
        if(noteCommand == null) {
            return null;
        }

        final Notes notes = new Notes();
        notes.setId(noteCommand.getId());
        notes.setRecipeNotes(noteCommand.getRecipeNotes());
        return notes;
    }
}