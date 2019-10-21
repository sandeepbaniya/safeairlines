package edu.mum.cs.cs452.safeairlines.controller;


import edu.mum.cs.cs452.safeairlines.model.Passenger;
import edu.mum.cs.cs452.safeairlines.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class SignupController {

    @Autowired
    PassengerService passengerService;

    @GetMapping("/signup")
    public String getSignupForm(@ModelAttribute("newPassenger") Passenger passenger) {
        return "signup";
    }


    @PostMapping("add")
    public String addPassenger(@Valid @ModelAttribute("newPassenger") Passenger passenger, Model model, BindingResult result) {
        System.out.println("==================================" + passenger.getEmail());
        if (result.hasErrors()) {

            return "public/signup";
        }
        passengerService.save(passenger);
        return "redirect:/user/login";
    }


}
