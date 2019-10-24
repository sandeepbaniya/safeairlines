package edu.mum.cs.cs452.safeairlines.controller;

import edu.mum.cs.cs452.safeairlines.model.BookingRecord;
import edu.mum.cs.cs452.safeairlines.model.Flight;
import edu.mum.cs.cs452.safeairlines.model.User;
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
import java.time.LocalDate;

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
    public String verifyInfo(@RequestParam ("flightId") Long flightId, Model model){
        model.addAttribute("flight",flightService.getFlightById(flightId));
        model.addAttribute("date", LocalDate.now());

        //System.out.println("id of User :"+userService.getUerByMail(principal.getName()).getId());

        return  "/private/checkout";
    }

    @GetMapping("confirmInfo")
    public String confirmationOfInfo(@RequestParam ("flightId") Long flightId,
                                     @RequestParam("card_number") String card_number ,
                                     @RequestParam ("cvv") String cvv, Principal principal,Model model){

        Flight flight = flightService.getFlightById(flightId);
        User user = userService.getUerByMail(principal.getName());

        BookingRecord record = new BookingRecord();
                    String confirmationCode = flight.getFlightNumber()+flight.getPlaneNumber()+"-"+user.getId();
                    record.setBookingDate(LocalDate.now());
                    record.setConfirmationCode(confirmationCode);
                    record.setFlight(flight);
                    user.addBookingRecord(record);
                    User userRecord = userService.save(user);




        model.addAttribute("flight",flight);
        model.addAttribute("user",userRecord);
        model.addAttribute("record",record);
         //we need to check information about his credit card
        // make some computation


        return "private/bookingReceipt";
    }






}
