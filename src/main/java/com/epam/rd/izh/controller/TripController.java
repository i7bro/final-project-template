package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.TripDto;
import com.epam.rd.izh.dto.TripsToursLeftJoinDto;
import com.epam.rd.izh.service.impl.TourServiceImpl;
import com.epam.rd.izh.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class TripController {

    private TripService tripService;
    private TourServiceImpl tourService;

    @Autowired
    public TripController(TripService tripService, TourServiceImpl tourService) {
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
            model.addAttribute("tourId", tourId);
        } else {
            trips = tripService.tripsToursLeftJoin();
        }

        model.addAttribute("trips", trips);
        model.addAttribute("user", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toArray()[0].toString());

        return "/trips";
    }

    @GetMapping({"/new_trip/{tourId}", "/new_trip/{tourId}/"})
    public String createTripByTourId(@PathVariable Integer tourId, Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getName());
        model.addAttribute("tourId", tourId);

        return "/new_trip";
    }

    @PostMapping("/new_trip")
    public String createTripByTourId(@ModelAttribute("newTripForm") TripDto tripDto) {

        tripService.save(tripDto);

        return "redirect:/trips?tourId=" + tripDto.getTourId();
    }

    @PostMapping("/trips/delete/{id}")
    public String deleteTrip(@PathVariable Integer id) {
        Integer tourId = tripService.findById(id).orElseThrow(NoSuchElementException::new).getTourId();

        tripService.delete(id);


        return "redirect:/trips?tourId=" + tourId;
    }

    @PostMapping("/trips/edit")
    public String updateTrip(@ModelAttribute("editTripForm") TripDto tripDto) {
        tripService.update(tripDto);

        return "redirect:/trips?tourId=" + tripDto.getTourId();
    }
}
