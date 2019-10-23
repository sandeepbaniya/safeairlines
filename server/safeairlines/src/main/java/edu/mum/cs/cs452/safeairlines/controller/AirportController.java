package edu.mum.cs.cs452.safeairlines.controller;

import edu.mum.cs.cs452.safeairlines.model.Airport;
import edu.mum.cs.cs452.safeairlines.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping(value = {"/new"})
    public String displayFormAddAirport(@ModelAttribute("airport") Airport airport){
        return  "airport/addAirport";
    }

    @PostMapping(value = {"/new"})
    public String addNewAirport(@Valid @ModelAttribute("flight") Airport airport, BindingResult result,
                                RedirectAttributes attributes, Model model){

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return  "airport/addAirport";
        }

        airportService.saveAirport(airport);
        attributes.addFlashAttribute("message", "Aiport successfull Added !!");
        return "redirect:/admin/airport/new";
    }
}
