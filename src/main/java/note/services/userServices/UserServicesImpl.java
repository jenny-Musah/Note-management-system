package note.services;

import note.data.dto.request.UserLogInRequest;
import note.data.dto.request.UserSignUpRequest;
import note.data.models.User;
import note.data.repository.UserRepository;
import note.utils.exceptions.InvalidInput;
import note.utils.validations.UserSignUpValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;


    @Override
    public String signUp(UserSignUpRequest userSignUpRequest) {
        validateSignUpRequest(userSignUpRequest);
       User savedUser = createUser(userSignUpRequest);
        return String.format("Welcome, %s %s",savedUser.getFirstName(),savedUser.getLastName());
    }

    private void validateSignUpRequest(UserSignUpRequest userSignUpRequest) {
        User alreadyExist = userRepository.findByEmail(userSignUpRequest.getEmail());
        if(alreadyExist != null) throw new InvalidInput("Email already exist");
        if(!UserSignUpValidation.isValidNames(userSignUpRequest.getFirstName())) throw new InvalidInput(String.format("%s is an invalid name", userSignUpRequest.getFirstName()));
        if(!UserSignUpValidation.isValidNames(userSignUpRequest.getLastName()))throw new InvalidInput(String.format("%s is an invalid name", userSignUpRequest.getLastName()));
        if(!UserSignUpValidation.isValidEmailAddress(userSignUpRequest.getEmail())) throw new InvalidInput(String.format("%s is an invalid emailAddress", userSignUpRequest.getEmail()));
        if(!UserSignUpValidation.isValidPassword(userSignUpRequest.getPassword())) throw new InvalidInput("Invalid password");
    }
    private User createUser(UserSignUpRequest userSignUpRequest){
        User user = new User();
        user.setLastName(userSignUpRequest.getLastName());
        user.setFirstName(userSignUpRequest.getFirstName());
        user.setEmail(userSignUpRequest.getEmail());
        user.setPassword(userSignUpRequest.getPassword());
        return userRepository.save(user);
    }

    @Override
    public String login(UserLogInRequest userLogInRequest) {
        User savedUser = userRepository.findByEmail(userLogInRequest.getEmail());
        if(savedUser != null){
            if(userLogInRequest.getPassword().matches(savedUser.getPassword())){
             return String.format("Welcome back, %s",savedUser.getFirstName());
            }
        }
        return "Unrecognized email or Password";
    }

}
