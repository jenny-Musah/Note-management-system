package note.services.userServices;

import note.data.dto.request.user_requests.UserLogInRequest;
import note.data.dto.request.user_requests.UserSignUpRequest;
import note.data.models.Note;
import note.data.models.User;
import note.data.repository.UserRepository;
import note.utils.exceptions.InvalidInput;
import note.utils.exceptions.UserNotfound;
import note.utils.validations.UserSignUpValidation;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        String password = BCrypt.hashpw(userSignUpRequest.getPassword(),BCrypt.gensalt());
        System.out.println(password);
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Override
    public String login(UserLogInRequest userLogInRequest) {
        User savedUser = userRepository.findByEmail(userLogInRequest.getEmail());
        if(savedUser != null){
            if(BCrypt.checkpw(userLogInRequest.getPassword(),savedUser.getPassword())){
             return String.format("Welcome back, %s",savedUser.getFirstName());
            }
        }
        return "Unrecognized email or Password";
    }

    @Override
    public void addNote(Note note, String userId) {
        User user = userRepository.findUserById(userId);
        if(user == null)throw new UserNotfound("User not found");
        user.getNotes().add(note);
        userRepository.save(user);
    }

    @Override
    public void deleteFromUserList(String noteId, String userId){
        User user = userRepository.findUserById(userId);
        if(user == null) throw new UserNotfound("User not found");
        user.getNotes().removeIf(note -> Objects.equals(note.getId(), noteId));
        userRepository.save(user);
    }
    @Override
    public void deleteAllNotes(String userId) {
        User user = userRepository.findUserById(userId);
        if(user == null) throw new UserNotfound("User not found");
        user.getNotes().clear();
        userRepository.save(user);
    }

    @Override
    public void deleteAccount(String userId) {
        User user = userRepository.findUserById(userId);
        if(user == null) throw new UserNotfound("User not found");
        userRepository.delete(user);
    }

    @Override
    public List<Note> userNoteList(String userId){
        User user = userRepository.findUserById(userId);
        if(user == null) throw new UserNotfound("User not found");
        return user.getNotes();
    }


}
