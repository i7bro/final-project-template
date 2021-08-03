package com.epam.rd.izh.controller;

import com.epam.rd.izh.service.UserRequestService;
import com.epam.rd.izh.service.impl.UserRequestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class UserRequestController {

    private final UserRequestService service;

    @GetMapping("/user_request")
    public String clientRegOnTrip(@RequestParam(required = false) String[] checkbox,
                                  Authentication authentication,
                                  RedirectAttributes redirectAttributes,
                                  HttpServletResponse response) {

        if (checkbox != null) {
            service.save(checkbox, authentication.getName());
            redirectAttributes.addAttribute("success", "You sign up successfully. Our managers will call you in 1 hour. Please wait");
        }

        return "redirect:/tours";
    }

    @PostMapping("/user_request/delete/{id}")
    public void deleteRequest(@PathVariable Integer id, HttpServletResponse response) {
        service.delete(id);

        response.setStatus(200);
    }
}
