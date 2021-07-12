package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.UserSettingsDto;
import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    UserServiceImpl userService;

    @Autowired
    public SettingsController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String viewSettings(@RequestParam(required = false) String status,
                               Authentication authentication, Model model) {
        User user = userService.getUserByLogin(authentication.getName());
        model.addAttribute("user", user.getLogin());
        model.addAttribute("userCur", user);
        model.addAttribute("role", authentication.getAuthorities().toArray()[0].toString());

        if (status != null) {
            model.addAttribute("status", status);
        }

        return "/settings";
    }

    @PostMapping
    public String changeData(@ModelAttribute("settingsForm") UserSettingsDto userDto) {
        userService.saveUser(userDto);

        return "redirect:/settings?status=success";
    }
}
