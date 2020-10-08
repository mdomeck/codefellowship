package com.mdomeck.codefellowship.controllers;

import com.mdomeck.codefellowship.models.post.Post;
import com.mdomeck.codefellowship.models.post.PostRepository;
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
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;


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

    @GetMapping("/user")
    public String whoToFollow(Model m){
        ArrayList<ApplicationUser> eachUser = (ArrayList<ApplicationUser>) applicationUserRepository.findAll();
        m.addAttribute("ArrayList", eachUser);
        return "userSearch";
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

    @PostMapping("/feed")
    public RedirectView feed(Principal principal, long whoIFollow){
        ApplicationUser whoIFollowUser = applicationUserRepository.getOne(whoIFollow);
        ApplicationUser myUser = applicationUserRepository.findByUsername(principal.getName());

        whoIFollowUser.whoIFollow.add(myUser);
        myUser.whoFollowsMe.add(whoIFollowUser);

        applicationUserRepository.save(whoIFollowUser);
        applicationUserRepository.save(myUser);
        return new RedirectView("/myprofile");
    }


    @GetMapping("/myprofile")
    public String showProfile(Model m, Principal principal) {
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        m.addAttribute("user", user);
        System.out.println(user.posts.size());
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
