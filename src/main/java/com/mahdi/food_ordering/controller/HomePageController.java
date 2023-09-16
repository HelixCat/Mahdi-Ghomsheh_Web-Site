package com.mahdi.food_ordering.controller;

import com.mahdi.food_ordering.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/signup")
    public String showSaveNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "sign_up";
    }
}
