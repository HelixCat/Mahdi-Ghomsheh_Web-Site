package com.mahdi.website.controller;

import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.service.GlobalServicesInterface;
import com.mahdi.website.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Controller
public class HomeController {

    private final UserServiceInterface userService;
    private final GlobalServicesInterface globalServices;

    @Autowired
    public HomeController(UserServiceInterface userService, GlobalServicesInterface globalServices) {
        this.userService = userService;
        this.globalServices = globalServices;
    }

    @GetMapping(value = "/profile/{username}")
    public String profile(@PathVariable String username, Model model) {
        String redirect = "redirect:";
        UserDTO userDetail = userService.loadUserDTOByUserName(username);
        if (Objects.nonNull(userDetail) && Objects.nonNull(userDetail.getAddressDTO())) {
            model.addAttribute("userDetail", userDetail);
            return "profile";
        } else {
            model.addAttribute("error", "Inactive user");
            redirect = redirect + "home";
        }
        return redirect;
    }

    @GetMapping("/home")
    public String returnToHome(UserDTO userDTO, Model model) {
        UserDTO userDetail = userService.loadUserDTOByEmail(userDTO.getEmail());
        model.addAttribute("userDetail", userDetail);
        return "home";
    }
}
