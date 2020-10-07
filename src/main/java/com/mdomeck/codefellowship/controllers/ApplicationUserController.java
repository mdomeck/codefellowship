package com.mdomeck.codefellowship.controllers;

import com.mdomeck.codefellowship.models.user.ApplicationUser;
import com.mdomeck.codefellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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
    public ModelAndView makeSignUp(String username,
                                   String password,
                                   String firstName,
                                   String lastName,
                                   Date dateOfBirth,
                                   String bio,
                                   HttpServletRequest request
    ) {
        System.out.println("adding new signup");
        password = passwordEncoder.encode((password));

        ApplicationUser newSignUp = new ApplicationUser(username, password, firstName, lastName, dateOfBirth, bio);
        System.out.println(newSignUp.toString());
        applicationUserRepository.save(newSignUp);
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

        // https://www.baeldung.com/spring-redirect-and-forward thanks Jack
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/myprofile")
    public String showProfile(Model m, Principal principal) {
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        m.addAttribute("user", user);
        return "myprofile";
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
