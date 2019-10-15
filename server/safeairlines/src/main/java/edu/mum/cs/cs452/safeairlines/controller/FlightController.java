package edu.mum.cs.cs452.safeairlines.controller;

import edu.mum.cs.cs452.safeairlines.model.Flight;
import edu.mum.cs.cs452.safeairlines.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FlightController {

    private FlightService flightService;

    public FlightController(FlightService flightService){
        this.flightService=flightService;
    }

    @GetMapping(value = {"/safeairline/flight/new"})
    public String displayFormAddFlight(@ModelAttribute("flight") Flight flight){
        return  "flight/addFlight";
    }

    @PostMapping(value = {"/safeairline/flight/new"})
    public String addNewFlight(Flight flight, RedirectAttributes attributes){
        attributes.addFlashAttribute("flight",flightService.saveFlight(flight));
        return "redirect:/safeairline/flight/success";
    }
    //this is for the PRG pattern (Post Redirect Get)
    @GetMapping(value = {"/safeairline/flight/success"})
    public String successFlight(){
        return "flight/saveSuccess";
    }

    @GetMapping(value = {"/safeairline/flight/list"})
    public String displayListFlight(Model model){
        model.addAttribute("flights",flightService.getAllFlights());
        return "flight/listFlight";
    }

}
