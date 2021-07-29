package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.TourDto;
import com.epam.rd.izh.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class ToursController {

    private final TourService tourService;

    @GetMapping({"/tours", "/tours/"})
    public String tours(Authentication authentication, ModelMap model,
                        @RequestParam(required = false) String error,
                        @RequestParam(required = false) String success,
                        @RequestParam(required = false) String direction) {
        List<List<Object[]>> toursGroup;

        if (direction != null) {
            toursGroup = tourService.getAllTourGroups4(direction);
        } else {
            toursGroup = tourService.getAllTourGroups4();
        }

        model.addAttribute("success", success);
        model.addAttribute("error", error);
        model.addAttribute("user", authentication.getName());
        model.addAttribute("toursGroups", toursGroup);
        model.addAttribute("role", authentication.getAuthorities().toArray()[0].toString());

        return "tours";
    }

    @PostMapping("/tours/edit_tour")
    public String editTour(@ModelAttribute("editTourForm") @Valid TourDto tourDto,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            tourService.updateTour(tourService.mapToTour(tourDto));
            redirectAttributes.addAttribute("success", "Data updated success.");
        } else {
            redirectAttributes.addAttribute("error", "Form is not valid!");
        }

        return "redirect:/tours";
    }

    @PostMapping("/tours/delete/{id}")
    public void deleteTour(@PathVariable("id") Integer id, HttpServletResponse response) {
        tourService.delete(id);

        response.setStatus(200);
    }

    @GetMapping({"/new_tour", "/new_tour/"})
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

    @PostMapping("/new_tour")
    public String createNewTour(@ModelAttribute("newTourForm") TourDto tourDto,
                                RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (tourService.findTourByTitle(tourDto.getTitle()) != null) {
                redirectAttributes.addAttribute("error", "title already exists, please, create new title");
                return "redirect:/new_tour";
            } else {
                tourService.save(tourService.mapToTour(tourDto));
                redirectAttributes.addAttribute("success", "Data updated success.");

                return "redirect:/tours";
            }
        }

        redirectAttributes.addAttribute("error", "Form is not valid!");
        return "redirect:/new_tour";
    }
}
