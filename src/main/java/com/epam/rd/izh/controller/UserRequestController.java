package com.epam.rd.izh.controller;

import com.epam.rd.izh.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserRequestController {

    UserRequestService service;

    @Autowired
    public UserRequestController(UserRequestService service) {
        this.service = service;
    }

    @GetMapping("/user_request")
    public String clientRegOnTrip(@RequestParam(required = false) String[] checkbox,
                                  Authentication authentication,
                                  RedirectAttributes redirectAttributes) {

        if (checkbox != null) {
            service.save(checkbox, authentication.getName());
            redirectAttributes.addAttribute("success", "You sign up successfully. Our managers will call you in 1 hour. Please wait");
        }

        return "redirect:/tours";
    }
}
