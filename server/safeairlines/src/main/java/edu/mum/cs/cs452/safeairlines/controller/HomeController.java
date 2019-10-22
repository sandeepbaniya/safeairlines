package edu.mum.cs.cs452.safeairlines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {

    @GetMapping({"/", "/home", "/index"})
    public String loadHome(){
        return "public/index";
    }

    public String getUsers(){
        return "users";
    }
}
//comment volcy