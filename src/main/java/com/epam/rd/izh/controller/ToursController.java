package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.Tour;
import com.epam.rd.izh.service.TourService;
import com.epam.rd.izh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ToursController {

    TourService service;

    @Autowired
    public ToursController(TourService service) {
        this.service = service;
    }

    @GetMapping("/tours")
    public String tours(Authentication authentication, Model model) {
        List<List<Tour>> toursGroup = service.getAllTourGroups4();

        model.addAttribute("userName", authentication.getName());
        model.addAttribute("toursGroups", toursGroup);

        return "tours";
    }
}
