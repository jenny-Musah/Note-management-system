package note.data.repository;

import note.data.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface NoteRepository extends MongoRepository<Note,String> {
    Note findNoteById(String id);

}
