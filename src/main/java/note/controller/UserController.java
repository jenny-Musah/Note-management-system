package note.controller;

import note.data.dto.request.user_requests.UserLogInRequest;
import note.data.dto.request.user_requests.UserSignUpRequest;
import note.services.userServices.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
   private UserServices userServices;

    @PostMapping("/sign_up")
    public ResponseEntity<?> signUp(@RequestBody UserSignUpRequest userSignUpRequest){
       return  new ResponseEntity<>(userServices.signUp(userSignUpRequest), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogInRequest userLogInRequest){
        return new ResponseEntity<>(userServices.login(userLogInRequest), HttpStatus.OK);
    }
    @DeleteMapping("/delete_account/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        userServices.deleteAccount(userId);
        return new ResponseEntity<>("Account deleted", HttpStatus.OK);
    }
}
