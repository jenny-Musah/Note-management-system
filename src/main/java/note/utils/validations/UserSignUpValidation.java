package note.utils.validations;

import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {

        return phoneNumber.matches("^\\\\+(?:[0-9] ?){6,14}[0-9]$");
    }

    public static boolean isValidEmailAddress(String email) {
        return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
    public static boolean isValidNames(String name){
        return name.matches("[A-Z][a-z]*");
    }
}
