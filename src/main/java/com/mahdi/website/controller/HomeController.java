package com.mahdi.website.controller;

import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;

@Controller
public class HomeController {

    private final IUserService userService;

    @Autowired
    public HomeController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/profile/{userName}")
    public String profile(@PathVariable String userName, Model model) {
        String redirect = "redirect:";
        UserDTO userDetail = userService.loadUserDTOByUserName(userName);
        if (Objects.nonNull(userDetail) && Objects.nonNull(userDetail.getAddressDTO())) {
            model.addAttribute("userDetail", userDetail);
            return "profile";
        } else {
            model.addAttribute("error", "Inactive user");
            redirect = redirect + "home";
        }
        return redirect;
    }
}
