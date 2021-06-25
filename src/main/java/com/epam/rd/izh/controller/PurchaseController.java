package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.PurchaseDto;
import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.service.impl.PurchaseServiceImpl;
import com.epam.rd.izh.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PurchaseController {

    private PurchaseServiceImpl purchaseService;
    private UserServiceImpl userService;

    @GetMapping("/user_purchases")
    public String purchases(Authentication authentication,
                            Model model) {

        User user = userService.getUserByLogin(authentication.getName());
        List<PurchaseDto> purchaseDtos = purchaseService.findByUserId(user.getId());

        model.addAttribute("purchases", purchaseDtos);
        model.addAttribute("user", user.getLogin());
        model.addAttribute("role", authentication.getAuthorities().toArray()[0].toString());

        return "/purchases";
    }
}
