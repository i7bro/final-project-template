package com.epam.rd.izh.controller;

import com.epam.rd.izh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  private final UserService userService;

  @Autowired
  public IndexController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/main")
  public String login(Authentication authentication, Model model) {
//    Message greetingMessage = new Message();
//    greetingMessage.setMessage(ENG_GREETING + authentication.getName());
//
    model.addAttribute("user", userService.getUserByLogin(authentication.getName()));
    return "main";
  }

}
