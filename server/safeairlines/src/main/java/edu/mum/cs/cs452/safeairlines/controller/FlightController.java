package edu.mum.cs.cs452.safeairlines.controller;

import edu.mum.cs.cs452.safeairlines.model.Flight;
import edu.mum.cs.cs452.safeairlines.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/safeairline/flight")
public class FlightController {

    private FlightService flightService;

    public FlightController(FlightService flightService){
        this.flightService=flightService;
    }

    @GetMapping(value = {"/new"})
    public String displayFormAddFlight(@ModelAttribute("flight") Flight flight){
        return  "flight/addFlight";
    }

    @PostMapping(value = {"/new"})
    public String addNewFlight(@Valid @ModelAttribute("flight") Flight flight, BindingResult result,
                               RedirectAttributes attributes, Model model){

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return  "flight/addFlight";
        }

        attributes.addFlashAttribute("flight",flightService.saveFlight(flight));
        return "redirect:/safeairline/flight/success";
    }
    //this is for the PRG pattern (Post Redirect Get)
    @GetMapping(value = {"/success"})
    public String successFlight(){
        return "flight/saveSuccess";
    }

    @GetMapping(value = {"/list"})
    public String displayListFlight(Model model){
        model.addAttribute("flights",flightService.getAllFlights());
        return "flight/listFlight";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteFlightById(@PathVariable("id") Long id){
        flightService.deleteFlightById(id);
        return "redirect:/safeairline/flight/list";
    }

     @GetMapping(value = "/edit/{id}")
     public String editFlight(@PathVariable("id") Long id, Model model){
       Flight flight = flightService.getFlightById(id);
        if(flight != null){
            model.addAttribute("flight",flight);
             return "flight/edit";
         }

     return  "/safeairline/flight/list";
    }

    @PostMapping("/edit")
    public String saveEdit(@Valid @ModelAttribute("flight") Flight flight,BindingResult result, Model model){

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return  "flight/edit";
        }
        flightService.saveFlight(flight);
        return "redirect:/safeairline/flight/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("searchCriteria") String searchCriteria, Model model){
        model.addAttribute("flights",flightService.getFlightBaseOnCriteria(searchCriteria));

        return "flight/listFlight";
    }

}
