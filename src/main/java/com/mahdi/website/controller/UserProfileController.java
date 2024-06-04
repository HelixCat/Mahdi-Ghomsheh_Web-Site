package com.mahdi.website.controller;

import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserProfileController {

    private final IUserService userService;

    @Autowired
    public UserProfileController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/profile/{userName}")
    public String updateUserProfile(@PathVariable String userName, UserDTO userDTO) {
        try {
            userService.updateUser(userName, userDTO);
            return "redirect:/profile";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
