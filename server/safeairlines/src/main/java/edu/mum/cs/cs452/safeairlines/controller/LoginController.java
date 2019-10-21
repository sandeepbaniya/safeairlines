package edu.mum.cs.cs452.safeairlines.controller;

import edu.mum.cs.cs452.safeairlines.model.Passenger;
import edu.mum.cs.cs452.safeairlines.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller

public class LoginController {

    @Autowired
    PassengerService passengerService;

    @GetMapping("/login")
    public String getLoginForm(@ModelAttribute("passenger") Passenger passenger) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newPassenger") Passenger passenger, Model model) {
        Passenger p = passengerService.checkExistingPassenger(passenger.getEmail());
        if (p != null) {
            model.addAttribute("passenger", p);

            return "public/home";
        }

        return "redirect:/login";
    }
}
