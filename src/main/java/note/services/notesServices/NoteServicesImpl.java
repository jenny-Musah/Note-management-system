package note.services.notesServices;

import note.data.dto.request.note_requests.AddNoteRequest;
import note.data.dto.response.NoteViewResponse;
import note.data.models.Note;
import note.data.repository.NoteRepository;
import note.services.userServices.UserServices;
import note.utils.exceptions.exceptionClass.NoteNoteFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NoteServicesImpl implements NoteService{
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    UserServices userServices;

    @Override
    public void createNote(AddNoteRequest addNoteRequest, String userId) {
    Note note = new Note();
    note.setBody(addNoteRequest.getBody());
    note.setTitle(addNoteRequest.getTitle());
    note.setUserId(userId);
    Note savedNote = noteRepository.save(note);
    userServices.addNote(savedNote, userId);
    }

    @Override
    public void deleteNoteById(String noteId, String userId) {
        Note note = noteRepository.findNoteById(noteId);
        if(note == null) throw  new NoteNoteFound();
        noteRepository.delete(note);
        userServices.deleteFromUserList(noteId,userId);
    }

    @Override
    public void deleteAllNotes(String userId) {
        noteRepository.deleteAll(userServices.userNoteList(userId));
        userServices.deleteAllNotes(userId);
    }

    @Override
    public List<NoteViewResponse> viewAllNotes(String userId) {
        List<NoteViewResponse> responses =  new ArrayList<>();
        for(Note note : noteRepository.findAll()){
            if(Objects.equals(note.getUserId(), userId)){
                NoteViewResponse noteViewResponse = new NoteViewResponse();
                noteViewResponse.setTitle(note.getTitle());
                noteViewResponse.setBody(note.getBody());
                noteViewResponse.setDate(note.getDate());
                responses.add(noteViewResponse);
            }
        }
        return responses;
    }

    @Override
    public NoteViewResponse viewNoteById(String noteId) {
        NoteViewResponse noteViewResponse = new NoteViewResponse();
        Note note = noteRepository.findNoteById(noteId);
        if(note == null) throw  new NoteNoteFound();
        noteViewResponse.setTitle(note.getTitle());
        noteViewResponse.setBody(note.getBody());
        noteViewResponse.setDate(note.getDate());
        return noteViewResponse;
    }

    @Override
    public void updateNote(String noteId, AddNoteRequest addNoteRequest) {
        Note note = noteRepository.findNoteById(noteId);
        if(note == null) throw  new NoteNoteFound();
        note.setBody(addNoteRequest.getBody());
        note.setTitle(addNoteRequest.getTitle());
        noteRepository.save(note);
    }

}
