package edu.mum.cs.cs452.safeairlines.controller;

import edu.mum.cs.cs452.safeairlines.model.User;
import edu.mum.cs.cs452.safeairlines.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller

public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String getLoginForm(@ModelAttribute("passenger") User user) {
        return "public/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newPassenger") User user, Model model) {
        User u = userService.checkExistingUser(user.getEmail());
        if (u != null) {
            model.addAttribute("user", u);

            return "public/home";
        }

        return "redirect:/login";
    }
}
