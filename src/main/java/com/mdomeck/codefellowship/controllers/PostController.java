package com.mdomeck.codefellowship.controllers;

import com.mdomeck.codefellowship.models.post.Post;
import com.mdomeck.codefellowship.models.post.PostRepository;
import com.mdomeck.codefellowship.models.user.ApplicationUser;
import com.mdomeck.codefellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;

@Controller
public class PostController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/myprofile")
    public RedirectView makeNewPost(String body, long id){
        Post post = new Post(body);
        ApplicationUser user = applicationUserRepository.getOne(id);

        post.applicationUser = user;

        postRepository.save(post);

        user.posts.add(post);
        applicationUserRepository.save(user);

        return new RedirectView("/myprofile");
    }

}
