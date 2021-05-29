package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.Tour;
import com.epam.rd.izh.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class ToursController {

    TourService tourService;

    @Autowired
    public ToursController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/tours")
    public String tours(Authentication authentication, Model model) {
        List<List<Tour>> toursGroup = tourService.getAllTourGroups4();

        model.addAttribute("user", authentication.getName());
        model.addAttribute("toursGroups", toursGroup);
        model.addAttribute("role", authentication.getAuthorities().toArray()[0].toString());

        return "tours";
    }
}
