package note.data.dto.request;

import lombok.Data;

@Data
public class UserLogInRequest {
    private String email;
    private String password;
}
