package edu.mum.cs.cs452.safeairlines.controller;

import edu.mum.cs.cs452.safeairlines.model.User;
import edu.mum.cs.cs452.safeairlines.service.RoleService;
import edu.mum.cs.cs452.safeairlines.service.UserService;
import edu.mum.cs.cs452.safeairlines.utils.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/registration")
    public String registration(@ModelAttribute User user){
        return "pages/security/register";
    }

    @PostMapping("/registration")
    public String saveUser(@Valid User user, BindingResult result, Model model){
        User userExists = userService.findUserByUserName(user.getUsername());
        if (userExists != null) {
            result
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");

        }
        if (result.hasErrors()) {
            return "pages/security/register";
        }
        user.setRole((roleService.getById(user.getRole().getId())));
        userService.saveUser(user);
        if (user.getRole().getId()== SecurityConstants.ROLE_ADMIN)
            model.addAttribute("successMessage", "Admin has been registered successfully!");
        else if (user.getRole().getId()== SecurityConstants.ROLE_BUYER)
            model.addAttribute("successMessage", "Buyer has been registered successfully");
        model.addAttribute("user", new User());
        return "pages/security/register";
    }
}