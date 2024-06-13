package com.mahdi.website.service.validation;

import com.mahdi.website.model.User;
import com.mahdi.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginValidation implements LoginValidationInterface{

    final UserService userService;

    @Autowired
    public LoginValidation(UserService userService) {
        this.userService = userService;
    }


    @Override
    public Boolean validateLoginRequest(String email, String Password) {
        User user = userService.loadUserByEmail(email);
        Boolean validPassword = Boolean.FALSE;
        if (Objects.nonNull(user)) {
            String hashedPassword = user.getPassword();
            validPassword = userService.isValidPassword(Password, hashedPassword);
        }
        return validPassword;
    }

}
