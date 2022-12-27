package note.services.userServices;

import note.data.dto.request.user_requests.UserLogInRequest;
import note.data.dto.request.user_requests.UserSignUpRequest;
import note.data.dto.response.NoteViewResponse;
import note.data.models.Note;

import java.util.List;

public interface UserServices {
    String signUp (UserSignUpRequest userSignUpRequest);
    String login(UserLogInRequest userLogInRequest);
    //hash password
    void addNote(Note note, String id );
    List<Note> userNoteList(String userId);
    void deleteAllNotes(String userId);
    void deleteAccount(String userId);
    void deleteFromUserList(String noteId, String userId);

}
