package edu.mum.cs.cs452.safeairlines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/booking")
public class BookingController {

    @GetMapping("/checkInfo")
    public String verifyInfo(@RequestParam ("flightId") Long flightId ){
        return  "";
    }
}
