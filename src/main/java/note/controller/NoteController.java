package note.controller;

import note.data.dto.request.note_requests.AddNoteRequest;
import note.data.dto.request.note_requests.FindNoteRequest;
import note.services.notesServices.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/add_note/{userId}")
    public ResponseEntity<?> addNote(@RequestBody AddNoteRequest addNoteRequest, @PathVariable String userId){
        noteService.createNote(addNoteRequest, userId);
        return  new ResponseEntity<>("Note added Successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{noteId}/{userId}")
    public ResponseEntity<?> deleteNoteById(@PathVariable String noteId, @PathVariable String userId){
        noteService.deleteNoteById(noteId,userId);
        return new ResponseEntity<>("Note deleted successfully", HttpStatus.OK);
    }
    @DeleteMapping("/delete_all/{userId}")
    public ResponseEntity<?> deleteAllNote(@PathVariable String userId){
        noteService.deleteAllNotes(userId);
        return new ResponseEntity<>("Note deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/notes/{userId}")
    public ResponseEntity<?> viewAllNote(@PathVariable String userId){
        return new ResponseEntity<>(noteService.viewAllNotes(userId), HttpStatus.OK);
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<?> viewNote(@PathVariable String id){
        return new ResponseEntity<>(noteService.viewNoteById(id), HttpStatus.OK);
    }

    @PostMapping("/update/{noteId}")
    public ResponseEntity<?> updateNote(@RequestBody AddNoteRequest addNoteRequest, @PathVariable String noteId){
        noteService.updateNote(noteId,addNoteRequest);
        return new ResponseEntity<>("Note update successful", HttpStatus.OK);
    }
    @PostMapping("/search/{userId}")
    public ResponseEntity<?> searchForNote(@RequestBody FindNoteRequest findNoteRequest, @PathVariable String userId){
       return new ResponseEntity<>(noteService.searchForNote(findNoteRequest,userId), HttpStatus.OK);
    }
}
