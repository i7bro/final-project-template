package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.service.UserService;
import com.epam.rd.izh.service.impl.UserServiceImpl;
import com.epam.rd.izh.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(required = false) String error,
                        @RequestParam(required = false) String success) {
        if (error != null) {
            model.addAttribute("error_login_placeholder", "invalid login or password!");
        }
        if (success != null) {
            model.addAttribute("success", success);
        }
        return "login";
    }

    @GetMapping("/registration")
    public String viewRegistration(Model model, @RequestParam(required = false) String error) {

        if (error != null) {
            model.addAttribute("error_login_exists", error);
        }
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "registration";
    }

    @PostMapping("/registration/proceed")
    public String processRegistration(User registeredUser, RedirectAttributes redirectAttributes) {

        if (userService.hasSameLogin(registeredUser.getLogin())) {
            redirectAttributes.addAttribute("error", "Login already exists, please, create new login");
            return "redirect:/registration";
        }

        registeredUser.setRole(Role.USER);
        registeredUser.setPassword(passwordEncoder.encode(registeredUser.getPassword()));

        userService.saveUser(registeredUser);

        redirectAttributes.addAttribute("success", "Registration is successful. Enter your login and password");
        return "redirect:/login";
    }

}
