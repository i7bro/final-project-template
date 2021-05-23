package com.epam.rd.izh.controller;

import com.epam.rd.izh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToursController {

    UserService userService;

    @Autowired
    public ToursController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/tours")
    public String tours(Model model) {
        return null;
    }
}
