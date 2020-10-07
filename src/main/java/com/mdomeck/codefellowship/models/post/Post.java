package com.mdomeck.codefellowship.models.post;

import com.mdomeck.codefellowship.models.user.ApplicationUser;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    public ApplicationUser applicationUser;

    String body;
    Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public Post(){};

    public Post(String body){
        this.body = body;
       // this.createdAt = createdAt;

    }

//    public String toString(){
//        return String.format(
//                ""
//        );
//    }

}
