package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.TourValidDto;
import com.epam.rd.izh.entity.Tour;
import com.epam.rd.izh.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

    @GetMapping("/tours/")
    public String toursSlash() {
        return "redirect:/tours";
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
    public String newTour(Authentication authentication, Model model,
                          @RequestParam(required = false) String error,
                          @RequestParam(required = false) String tour) {

        if (error != null) {
            model.addAttribute("error", error);
        }
        if (tour != null) {
            model.addAttribute("tour", tour);
        }
        model.addAttribute("user", authentication.getName());

        return "new_tour";
    }

    @GetMapping("/new_tour/")
    public String newTourSlash() {
        return "redirect:/new_tour";
    }

    @PostMapping("/new_tour")
    public String createNewTour(@ModelAttribute("newTourForm") @Valid TourValidDto tourValidDto,
                                RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (tourService.findTourByTitle(tourValidDto) != null) {
                redirectAttributes.addAttribute("error", "title already exists, please, create new title");
                return "redirect:/new_tour";
            } else {
                tourService.save(tourService.getTour(tourValidDto));
                redirectAttributes.addAttribute("success", "Data updated success.");

                return "redirect:/tours";
            }
        }

        redirectAttributes.addAttribute("error", "Form is not valid!");
        return "redirect:/new_tour";
    }
}
