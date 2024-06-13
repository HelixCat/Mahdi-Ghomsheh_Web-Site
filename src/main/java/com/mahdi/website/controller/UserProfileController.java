package com.mahdi.website.controller;

import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/change-password-user/{username}")
    public String updateUserPassword(@PathVariable String username, ChangePasswordDTO changePasswordDTO, Model model) {
        try {
            userService.updateUserPassword(username, changePasswordDTO);
            UserDTO userDetail = userService.loadUserDTOByUserName(username);
            model.addAttribute("userDetail", userDetail);
            return "profile";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/change-password/{username}")
    public String updateUserPasswordPage(@PathVariable String username, Model model) {
        try {
            ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
            changePasswordDTO.setUserName(username);
            model.addAttribute("changePasswordDTO", changePasswordDTO);
            return "change_password_page";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
