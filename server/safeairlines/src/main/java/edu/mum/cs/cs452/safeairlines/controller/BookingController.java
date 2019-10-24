package edu.mum.cs.cs452.safeairlines.controller;

import edu.mum.cs.cs452.safeairlines.email.EmailService;
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
import edu.mum.cs.cs452.safeairlines.model.Airport;
import edu.mum.cs.cs452.safeairlines.model.Flight;
import edu.mum.cs.cs452.safeairlines.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
@Controller
@RequestMapping("/user/booking")
public class BookingController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServletContext context;

    @Autowired
    EmailService emailService;

//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    // Check if user is authenticate

//    public boolean isAuthenticate(){
//        return authentication instanceof AnonymousAuthenticationToken;
//    }

    @GetMapping("/checkInfo")
    public String verifyInfo(@RequestParam("flightId") Long flightId, Model model) {
        model.addAttribute("flight", flightService.getFlightById(flightId));
        model.addAttribute("date", LocalDate.now());

        //System.out.println("id of User :"+userService.getUerByMail(principal.getName()).getId());

        return "/private/checkout";
    }

    @GetMapping("confirmInfo")
    public String confirmationOfInfo(@RequestParam("flightId") Long flightId,
                                     @RequestParam("card_number") String card_number,
                                     @RequestParam("cvv") String cvv, Principal principal, Model model) {

        Flight flight = flightService.getFlightById(flightId);
        User user = userService.getUerByMail(principal.getName());

        BookingRecord record = new BookingRecord();
        String confirmationCode = flight.getFlightNumber() + flight.getPlaneNumber() + "-" + user.getId();
        record.setBookingDate(LocalDate.now());
        record.setConfirmationCode(confirmationCode);
        record.setFlight(flight);
        user.addBookingRecord(record);
        User userRecord = userService.save(user);



        model.addAttribute("flight", flight);
        model.addAttribute("user", userRecord);
        model.addAttribute("record", record);
        //we need to check information about his credit card
        // make some computation

        String to = user.getEmail();
        String subject = "SafeAirlines - You have a flight Booking";
        String text = "Hello, " + user.getFullName() + " You have confirmed a booking. Booking Confirmation Code: " +
                record.getConfirmationCode() + " ,Flight Number: " + flight.getFlightNumber() + " ,Plane: " + flight.getPlaneNumber();

        emailService.sendSimpleMessage(to,subject,text);


        return "private/bookingReceipt";
    }

    // Report Methode
    @GetMapping(value = "/report")
    public void flightPDF(HttpServletRequest request, HttpServletResponse response) {

        List<Flight> flight = flightService.getAllFlights();
         boolean isFlag = flightService.generatePDF(flight, context, request, response);
        if (isFlag) {
             String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "flights" + ".pdf");
            filedownload(fullPath, response, "flights.pdf");
        }

    }
    private void filedownload(String fullPath, HttpServletResponse response, String filename) {
        File file = new File(fullPath);
        final int BUFFER_SIZE = 4096;
        if (file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                String mimeType = context.getMimeType(fullPath);
                response.setContentType(mimeType);
                response.setHeader("content-disposition", "attachment; filename=" + filename);
                OutputStream outputStream = response.getOutputStream();
                byte buffer[] = new byte[BUFFER_SIZE];
                int byteReads = -1;
                while ((byteReads = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, byteReads);
                }
                inputStream.close();
                outputStream.close();
                file.delete();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



}
