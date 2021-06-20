package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.TripsToursLeftJoinDto;
import com.epam.rd.izh.entity.Tour;
import com.epam.rd.izh.entity.Trip;
import com.epam.rd.izh.service.TourService;
import com.epam.rd.izh.service.TripServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TripController {

    private TripServiceI tripService;
    private TourService tourService;

    @Autowired
    public TripController(TripServiceI tripService, TourService tourService) {
        this.tripService = tripService;
        this.tourService = tourService;
    }

    @GetMapping("/trips")
    public String trips(Model model,
                        @RequestParam(required = false) Integer tourId,
                        Authentication authentication) {
        List<TripsToursLeftJoinDto> trips;
        if (tourId != null) {
            trips = tripService.tripsToursLeftJoinByTourId(tourId);
        } else {
            trips = tripService.tripsToursLeftJoin();
        }

        model.addAttribute("trips", trips);
        model.addAttribute("user", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toArray()[0].toString());

        return "/trips";
    }
}
