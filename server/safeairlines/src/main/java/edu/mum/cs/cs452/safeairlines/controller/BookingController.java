package edu.mum.cs.cs452.safeairlines.controller;

import edu.mum.cs.cs452.safeairlines.service.FlightService;
import edu.mum.cs.cs452.safeairlines.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/user/booking")
public class BookingController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private UserService userService;

//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    // Check if user is authenticate

//    public boolean isAuthenticate(){
//        return authentication instanceof AnonymousAuthenticationToken;
//    }

    @GetMapping("/checkInfo")
    public String verifyInfo(@RequestParam ("flightId") Long flightId, Principal principal, Model model){
        model.addAttribute("user",userService.getUerByMail(principal.getName()));
        model.addAttribute("flight",flightService.getFlightById(flightId));

        System.out.println(principal.getName());
        System.out.println(principal.getName());
        System.out.println("id of User :"+userService.getUerByMail(principal.getName()).getId());

        return  "/private/detailBooking";
    }





}
