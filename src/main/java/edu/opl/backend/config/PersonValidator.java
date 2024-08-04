package edu.opl.backend.config;

import edu.opl.backend.dto.Person;
import edu.opl.backend.exception.EmptyValuePassedException;
import edu.opl.backend.exception.IllegalValuePassedException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PersonValidator {

    public boolean isValidPerson(Person person) {
        if (person == null)
            throw new EmptyValuePassedException("Person is empty");
        return isValidEmail(person.getEmail())
                && isValidUsername(person.getUsername())
                && isValidPassword(person.getPassword());
    }

    private boolean isValidEmail(String email) {
        if (email.isBlank() || email.isEmpty())
            throw new IllegalValuePassedException("Email is not provided");
        Pattern p = Pattern.compile("^[a-z0-9.*%±]{2,}+@gmail.com$");
        Matcher m = p.matcher(email);
        if (!m.find())
            throw new IllegalValuePassedException("Email is not valid");
        return true;
    }

    private boolean isValidUsername(String username) {
        if (username.isBlank() || username.isEmpty())
            throw new IllegalValuePassedException("Username is not provided");
        Pattern p = Pattern.compile("[a-zA-Z0-9@]+");
        Matcher m = p.matcher(username);
        if (!m.find())
            throw new IllegalValuePassedException("Username is not valid");
        return true;
    }

    private boolean isValidPassword(String password) {
        if (password.isBlank() || password.isEmpty())
            throw new IllegalValuePassedException("Password is not provided");
        Pattern p = Pattern.compile("[0-9a-zA-Z!*@#$^%/]{8,}+");
        Matcher m = p.matcher(password);
        if (!m.find())
            throw new IllegalValuePassedException("Password is not valid");
        return true;
    }
}
