package note.utils.exceptions;

import note.utils.exceptions.exceptionClass.InvalidInput;
import note.utils.exceptions.exceptionClass.NoteNoteFound;
import note.utils.exceptions.exceptionClass.UserNotfound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
    private  ResponseEntity<Object> entity;
    private  ExceptionResponse response = new ExceptionResponse();

    @ExceptionHandler(NoteNoteFound.class)
    public ResponseEntity<Object> noteException(NoteNoteFound exception, WebRequest webRequest){
        response.setMessage("Note not found");
        response.setDate(LocalDateTime.now());
   return entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotfound.class)
    public ResponseEntity<Object> userException(UserNotfound exception, WebRequest webRequest){
        response.setMessage("User not Found");
        response.setDate(LocalDateTime.now());
        return entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
      @ExceptionHandler(InvalidInput.class)
    public ResponseEntity<Object> userException(InvalidInput exception, WebRequest webRequest){
        response.setMessage("Invalid input");
        response.setDate(LocalDateTime.now());
        return entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

}