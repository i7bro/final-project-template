package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.UserPasswordDto;
import com.epam.rd.izh.service.UserService;
import com.epam.rd.izh.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/change-password")
public class ChangePasswordController {

    private final UserService userService;

    @GetMapping
    public String changePassword(@RequestParam(required = false) String status,
                                 Authentication authentication, Model model) {
        UserPasswordDto userPasswordDto = userService.getUserPasswordDto(authentication.getName());

        model.addAttribute("user", authentication.getName());
        model.addAttribute("userDto", userPasswordDto);
        model.addAttribute("role", authentication.getAuthorities().toArray()[0].toString());
        model.addAttribute("status", status);

        return "/change_password";
    }

    @PostMapping
    public String changePassword(UserPasswordDto userPasswordDto) {
        if (userService.checkPassword(userPasswordDto)) {
            userService.saveUser(userPasswordDto);

            return "redirect:/change-password?status=success";
        } else {
            return "redirect:/change-password?status=error";
        }
    }
}
