package note.controller;

import note.data.dto.request.note_requests.AddNoteRequest;
import note.data.dto.response.NoteViewResponse;
import note.services.notesServices.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/add_note/{userId}")
    public String addNote(@RequestBody AddNoteRequest addNoteRequest, @PathVariable String userId){
        noteService.createNote(addNoteRequest, userId);
        return "Note added Successfully!";
    }

    @DeleteMapping("/delete/{noteId}/{userId}")
    public String deleteNoteById(@PathVariable String noteId, @PathVariable String userId){
        noteService.deleteNoteById(noteId,userId);
        return "Note deleted successfully";
    }
    @DeleteMapping("/delete_all/{userId}")
    public String deleteAllNote(@PathVariable String userId){
        noteService.deleteAllNotes(userId);
        return "All notes deleted successfully";
    }

    @GetMapping("/notes/{userId}")
    public List<NoteViewResponse> viewAllNote(@PathVariable String userId){
        return noteService.viewAllNotes(userId);
    }

    @GetMapping("/note/{id}")
    public NoteViewResponse viewNote(@PathVariable String id){
        return noteService.viewNoteById(id);
    }

    @PostMapping("/update/{noteId}")
    public String updateNote(@RequestBody AddNoteRequest addNoteRequest, @PathVariable String noteId){
        noteService.updateNote(noteId,addNoteRequest);
        return "Note update successful";
    }
    @GetMapping("/")
    public String test(){
        return "This is Spring";
    }

}
