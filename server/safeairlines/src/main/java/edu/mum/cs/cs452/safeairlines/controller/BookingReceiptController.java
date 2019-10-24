package edu.mum.cs.cs452.safeairlines.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingReceiptController {

    @GetMapping("/receipt")
    public String getReceipt(){
        return "private/bookingReceipt";
    }
}


