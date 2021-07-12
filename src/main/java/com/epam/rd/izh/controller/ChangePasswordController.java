package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.UserPasswordDto;
import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/change-password")
public class ChangePasswordController {

    UserServiceImpl userService;

    @Autowired
    public ChangePasswordController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String changePassword(@RequestParam(required = false) String status,
                                 Authentication authentication, Model model) {
        User user = userService.getUserByLogin(authentication.getName());

        model.addAttribute("user", user.getLogin());
        model.addAttribute("id", user.getId());
        model.addAttribute("userCur", user);
        model.addAttribute("role", authentication.getAuthorities().toArray()[0].toString());
        model.addAttribute("status", status);

        return "/change_password";
    }

    @PostMapping
    public String changePassword(@ModelAttribute("changePasswordForm") UserPasswordDto userPasswordDto) {
        if (userService.checkPassword(userPasswordDto)) {
            userService.saveUser(userPasswordDto);

            return "redirect:/change-password?status=success";
        } else {
            return "redirect:/change-password?status=error";
        }
    }
}
