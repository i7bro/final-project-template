package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.TourValidDto;
import com.epam.rd.izh.entity.Tour;
import com.epam.rd.izh.service.TourService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;


@Controller
public class ToursController {

    TourService tourService;

    @Autowired
    public ToursController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/tours")
    public String tours(Authentication authentication, Model model,
                        @RequestParam(required = false) String error, @RequestParam(required = false) String success) {
        List<List<Tour>> toursGroup = tourService.getAllTourGroups4();

        model.addAttribute("success", success);
        model.addAttribute("error", error);
        model.addAttribute("user", authentication.getName());
        model.addAttribute("toursGroups", toursGroup);
        model.addAttribute("role", authentication.getAuthorities().toArray()[0].toString());

        return "tours";
    }

    @PostMapping("/tours/edit_tour")
    public String editTour(@ModelAttribute("editTourForm") @Valid TourValidDto tourValidDto,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            tourService.updateTour(tourService.getTour(tourValidDto));
            redirectAttributes.addAttribute("success", "Data updated success.");
        } else {
            redirectAttributes.addAttribute("error", "Form is not valid!");
        }

        return "redirect:/tours";
    }

    @PostMapping("/tours/delete/{id}")
    public String deleteTour(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        tourService.delete(id);

        redirectAttributes.addAttribute("success", "Data updated success.");
        return "redirect:/tours";
    }

    @GetMapping("/new_tour")
    public String newTour(Authentication authentication, Model model) {
        model.addAttribute("user", authentication.getName());

        return "new_tour";
    }

    @PostMapping("/new_tour")
    public String createNewTour(@ModelAttribute("newTourForm") Tour tour, RedirectAttributes redirectAttributes) {
        tourService.save(tour);

        redirectAttributes.addAttribute("success", "Data updated success.");

        return "redirect:/tours";
    }
}
