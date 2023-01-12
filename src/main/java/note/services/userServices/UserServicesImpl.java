package note.services.userServices;

import note.data.dto.request.user_requests.UserLogInRequest;
import note.data.dto.request.user_requests.UserSignUpRequest;
import note.data.models.Note;
import note.data.models.User;
import note.data.repository.UserRepository;
import note.utils.exceptions.exceptionClass.InvalidInput;
import note.utils.exceptions.exceptionClass.UserNotfound;
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
        if(alreadyExist != null) throw new InvalidInput("User with this email already exist");
        if(!UserSignUpValidation.isValidNames(userSignUpRequest.getFirstName())) throw new InvalidInput("Invalid name format begin with capital letter");
        if(!UserSignUpValidation.isValidNames(userSignUpRequest.getLastName()))throw new InvalidInput("Invalid name format begin with capital letter");
        if(!UserSignUpValidation.isValidEmailAddress(userSignUpRequest.getEmail())) throw new InvalidInput("Invalid email address");
        if(!UserSignUpValidation.isValidPassword(userSignUpRequest.getPassword())) throw new InvalidInput("Wrong password format must include capital letter, small letter, special characters and numbers");
    }
    private User createUser(UserSignUpRequest userSignUpRequest){
        User user = new User();
        user.setLastName(userSignUpRequest.getLastName());
        user.setFirstName(userSignUpRequest.getFirstName());
        user.setEmail(userSignUpRequest.getEmail());
        String password = BCrypt.hashpw(userSignUpRequest.getPassword(),BCrypt.gensalt());
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
        if(user == null)throw new UserNotfound("User with this id does not exist");
        user.getNotes().add(note);
        userRepository.save(user);
    }

    @Override
    public void deleteFromUserList(String noteId, String userId){
        User user = userRepository.findUserById(userId);
        if(user == null) throw new UserNotfound("User with this id does not exist");
        user.getNotes().removeIf(note -> Objects.equals(note.getId(), noteId));
        userRepository.save(user);
    }
    @Override
    public void deleteAllNotes(String userId) {
        User user = userRepository.findUserById(userId);
        if(user == null) throw new UserNotfound("User with this id does not exist");
        user.getNotes().clear();
        userRepository.save(user);
    }

    @Override
    public void deleteAccount(String userId) {
        User user = userRepository.findUserById(userId);
        if(user == null) throw new UserNotfound("User with this id does not exist");
        userRepository.delete(user);
    }

    @Override
    public List<Note> userNoteList(String userId){
        User user = userRepository.findUserById(userId);
        if(user == null) throw new UserNotfound("User with this id does not exist");
        return user.getNotes();
    }


}
