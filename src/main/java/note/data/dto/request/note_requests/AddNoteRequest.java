package note.data.dto.request.note_requests;

import lombok.Data;

@Data
public class AddNoteRequest {
    private String title;
    private String body;
}
