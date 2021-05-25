package com.epam.rd.izh.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

    @GetMapping("/fragments")
    public String fragments(Authentication authentication, Model model) {

        model.addAttribute("userName", authentication.getName());
        return "fragments";
    }
}
