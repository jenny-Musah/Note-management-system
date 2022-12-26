package note.services;

import note.data.dto.request.UserLogInRequest;
import note.data.dto.request.UserSignUpRequest;

public interface UserServices {
    String signUp (UserSignUpRequest userSignUpRequest);
    String login(UserLogInRequest userLogInRequest);
}
