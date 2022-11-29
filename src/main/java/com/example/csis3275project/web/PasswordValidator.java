package com.example.csis3275project.web;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class PasswordValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        // Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character
        String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.compile(regexPattern)
                .matcher(s)
                .matches();
    }
}
