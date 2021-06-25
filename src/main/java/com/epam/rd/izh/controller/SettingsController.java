package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingsController {

    UserServiceImpl userService;

    @Autowired
    public SettingsController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/settings")
    public String viewSettings(Authentication authentication, Model model) {
        User user = userService.getUserByLogin(authentication.getName());
        model.addAttribute("user", user.getLogin());
        model.addAttribute("userCur", user);
        model.addAttribute("role", authentication.getAuthorities().toArray()[0].toString());

        return "settings";
    }

    // TODO: 5/17/2021 Add function to change user data and pass 
}
