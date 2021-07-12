package com.epam.rd.izh.controller;

import com.epam.rd.izh.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  private final UserServiceImpl userService;

  @Autowired
  public IndexController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping({"/", "/main"})
  public String login(Authentication authentication, Model model) {

    model.addAttribute("user", authentication.getName());
    model.addAttribute("role", authentication.getAuthorities().toArray()[0].toString());
    return "main";
  }

}
