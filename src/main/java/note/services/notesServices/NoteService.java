package note.services.notesServices;

import note.data.dto.request.note_requests.AddNoteRequest;
import note.data.dto.request.note_requests.FindNoteRequest;
import note.data.dto.response.NoteViewResponse;

import java.util.List;

public interface NoteService {
   void createNote(AddNoteRequest addNoteRequest, String userId);

   void deleteNoteById(String noteId, String userId);

   void deleteAllNotes(String userId);

   List<NoteViewResponse> viewAllNotes(String userId);

   NoteViewResponse viewNoteById(String noteId);

   void updateNote(String noteId,AddNoteRequest addNoteRequest);
   NoteViewResponse searchForNote(FindNoteRequest findNoteRequest, String userId);


}
