package com.mahdi.website.service.businesslogic;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.Address;
import com.mahdi.website.model.User;
import com.mahdi.website.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBusiness implements UserBusinessInterface{

    private final PasswordEncoder passwordEncoder;
    private final IUserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserBusiness(PasswordEncoder passwordEncoder, IUserService userService, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean isValidPasswordForLogin(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
