package edu.mum.cs.cs452.safeairlines.controller;

import edu.mum.cs.cs452.safeairlines.model.Flight;
import edu.mum.cs.cs452.safeairlines.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/fligths")
public class PublicFlightController {

    @Autowired
    private FlightService flightService;

    public PublicFlightController(FlightService flightService){
        this.flightService=flightService;
    }

    @PostMapping("/search")
    public String search(@RequestParam String from,
                         @RequestParam String where,
                         @RequestParam Date checkIn,
                         @RequestParam Long travelers,
                         Model model){
        List<Flight> flights = this.flightService.findAllByDepaturePlaceAndArrivalPlaceAndDeptDate(from, where, checkIn);
        model.addAttribute("flights", flights);
        return "flight/listFlight";
    }

    @PostMapping("/book")
    public String bookFlight(@PathVariable("id") Long id) {
        return "flight/confirmation";
    }
}
