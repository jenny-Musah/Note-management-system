package note.utils.exceptions;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ExceptionResponse {

    private String message;
    private LocalDateTime date;

}
