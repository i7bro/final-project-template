package com.epam.rd.izh.controller;

import com.epam.rd.izh.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

    UserServiceImpl service;

    @Autowired
    public FragmentController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/fragments")
    public String fragments() {
        return "fragments";
    }
}
