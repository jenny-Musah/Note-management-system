package note.controller;

import note.data.dto.request.user_requests.UserLogInRequest;
import note.data.dto.request.user_requests.UserSignUpRequest;
import note.services.userServices.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
   private UserServices userServices;

    @PostMapping("/sign_up")
    public String signUp(@RequestBody UserSignUpRequest userSignUpRequest){
       return userServices.signUp(userSignUpRequest);
    }
    @PostMapping("/login")
    public String login(@RequestBody UserLogInRequest userLogInRequest){
        return userServices.login(userLogInRequest);
    }

    @DeleteMapping("/delete_account/{userId}")
    public String deleteUser(@PathVariable String userId){
        userServices.deleteAccount(userId);
        return "Account deleted";
    }
}
