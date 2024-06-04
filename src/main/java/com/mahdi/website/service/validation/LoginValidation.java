package com.mahdi.website.service.validation;

import com.mahdi.website.service.businesslogic.UserBusinessInterface;
import com.mahdi.website.model.User;
import com.mahdi.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginValidation implements LoginValidationInterface{

    @Autowired
    UserService userService;

    @Autowired
    UserBusinessInterface userBusiness;

    @Override
    public Boolean validateLoginRequest(String email, String Password) {
        User user = userService.loadUserByEmail(email);
        Boolean validPassword = Boolean.FALSE;
        if (Objects.nonNull(user)) {
            String hashedPassword = user.getPassword();
            validPassword = userBusiness.isValidPasswordForLogin(Password, hashedPassword);
        }
        return validPassword;
    }

}
