package edu.mum.cs.cs452.safeairlines.controller;


import edu.mum.cs.cs452.safeairlines.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {


    @Autowired
    EmailService emailService;

    @GetMapping("/public/email")
    public void sendEmail(){
        String to = "ebusinessguide88@gmail.com";
        String subject = "Hey whats up";
        String text = "This is a test email";
        emailService.sendSimpleMessage(to, subject, text);

    }

}
