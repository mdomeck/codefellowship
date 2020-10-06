package com.mdomeck.codefellowship.controllers;


import com.mdomeck.codefellowship.models.user.ApplicationUser;
import com.mdomeck.codefellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Date;


@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user/{id}")
    public String showUserDetailsPage(@PathVariable Long id, Model m) {
        ApplicationUser user = applicationUserRepository.getOne(id);
        m.addAttribute("user", user);
        if (user == null) {
            m.addAttribute("userDoesNotExist", true);
        }
        return "userdetail";
    }

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @PostMapping("/intoDatabase")
    public RedirectView makeSignUp(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio) {
        System.out.println("adding new signup");
        password = passwordEncoder.encode((password));

        ApplicationUser newSignUp = new ApplicationUser(username, password, firstName, lastName, dateOfBirth, bio);
        System.out.println(newSignUp.toString());
        applicationUserRepository.save(newSignUp);

        return new RedirectView("/");
    }


    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
