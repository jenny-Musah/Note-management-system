package note.data.dto.request.user_requests;

import lombok.Data;

@Data
public class UserLogInRequest {
    private String email;
    private String password;
}
