package edu.mum.cs.cs452.safeairlines.controller;

import edu.mum.cs.cs452.safeairlines.model.Flight;
import edu.mum.cs.cs452.safeairlines.service.AirportService;
import edu.mum.cs.cs452.safeairlines.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller

public class HomeController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private FlightService flightService;

    @GetMapping({"/", "/home", "/index"})
    public String loadHome(Model model){
        model.addAttribute("airports",airportService.findAllAirport());
        return "public/index";
    }

    @GetMapping("flights/search")
    public String searchAvailableFlight(@RequestParam("from") Long from, @RequestParam("where") Long where,
                                        @RequestParam("deptDate") String deptDate,
                                        @RequestParam("returnDate") String returnDate, Model model, RedirectAttributes attributes){
        //Test for empty value
        if(from==null || where==null || deptDate==null || returnDate == null || deptDate.equals("")|| returnDate.equals("") ){
            attributes.addFlashAttribute("messages","Fill all the form properly");

            return "redirect:/";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate deptDateLocalDate = LocalDate.parse(deptDate, formatter);
        LocalDate returnDateLocalDate = LocalDate.parse(returnDate, formatter);
        List<Flight> flights = flightService.listFlightForBooking(deptDateLocalDate,returnDateLocalDate,from,where);
        model.addAttribute("flights",flights);
        model.addAttribute("size",flights.size());

        return  "public/listFlightAvailable";
    }

    public String getUsers(){
        return "users";
    }

    @GetMapping("/checkout")
    public String chekoutPage(){
        return "private/checkout";
    }



}
