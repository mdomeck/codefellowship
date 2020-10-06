package com.mdomeck.codefellowship.controllers;


import com.mdomeck.codefellowship.models.user.ApplicationUser;
import com.mdomeck.codefellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView makeSignUp(String username, String password){
        System.out.println("adding new signup");
        password = passwordEncoder.encode((password));

        ApplicationUser newSignUp = new ApplicationUser(username, password);

        applicationUserRepository.save(newSignUp);

        return new RedirectView("/");
    }


    @GetMapping("/login")
    public String login (){
        return "login";
    }

}
