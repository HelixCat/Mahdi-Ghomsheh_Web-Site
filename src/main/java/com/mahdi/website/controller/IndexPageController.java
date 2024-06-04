package com.mahdi.website.controller;

import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.service.IUserService;
import com.mahdi.website.service.validation.LoginValidationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class IndexPageController {

    private final IUserService userService;

    private final LoginValidationInterface loginValidation;


    @Autowired
    public IndexPageController(IUserService userService, LoginValidationInterface loginValidation) {
        this.userService = userService;
        this.loginValidation = loginValidation;
    }

    @GetMapping("")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/signup")
    public String showSaveNewUserForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "sign_up";
    }

    @PostMapping("/save")
    public String saveUser(UserDTO userDTO) throws Exception {
        userService.saveUser(userDTO);
        return "redirect:login";
    }

    @GetMapping("/login")
    public String showLoginUserForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "login";
    }

    @PostMapping ("/sign_in")
    public String login(UserDTO userDTO, Model model) throws Exception {
        String redirect = "redirect:";
        if (Objects.nonNull(userDTO)) {
            Boolean validateRequest = loginValidation.validateLoginRequest(userDTO.getEmail(), userDTO.getPassword());
            if (Boolean.TRUE.equals(validateRequest)) {
                model.addAttribute("userDetail", userDTO);
                return "home";
            } else {
                // User is invalid, return an error view or redirect to the login page with an error message
                model.addAttribute("error", "Invalid credentials");
                redirect = redirect + "login";
            }
        }
        return redirect;
    }
}
