package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

    UserService service;

    @Autowired
    public FragmentController(UserService service) {
        this.service = service;
    }

    @GetMapping("/fragments")
    public String fragments() {
        return "fragments";
    }
}
