package note.data.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NoteViewResponse {
    private String title;
    private String body;
    private LocalDate date;
}
