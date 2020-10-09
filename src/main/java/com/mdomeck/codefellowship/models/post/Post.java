package com.mdomeck.codefellowship.models.post;

import com.mdomeck.codefellowship.models.user.ApplicationUser;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    public ApplicationUser applicationUser;

    public String body;
    public Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    public String firstName;

    public Post(){};

    public Post(String body, String firstName){
        this.body = body;
        this.firstName = firstName;
       // this.createdAt = createdAt;

    }

    public long getId() {
        return id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }



    public String getBody() {
        return body;
    }


}
