package com.mdomeck.codefellowship.models.user;

import com.mdomeck.codefellowship.models.post.Post;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    String username;
    String password;
    String firstName;
    String lastName;
    Date dateOfBirth;
    String bio;
    public String image = "https://via.placeholder.com/150";

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
    public List<Post> posts = new ArrayList<Post>();

    @ManyToMany(cascade = CascadeType.REMOVE)

    @JoinTable(
            name="connecting",
            joinColumns = { @JoinColumn(name="giver")},
            inverseJoinColumns = {@JoinColumn(name = "receiver")}
    )
    public Set<ApplicationUser> whoIFollow = new HashSet<>();

    @ManyToMany(mappedBy = "whoIFollow")
    public Set<ApplicationUser> whoFollowsMe = new HashSet<>();


    public ApplicationUser(){};

    public ApplicationUser(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;


    }
    public Set<ApplicationUser> getWhoIFollow() {
        return whoIFollow;
    }

    public Set<ApplicationUser> getWhoFollowsMe() {
        return whoFollowsMe;
    }
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }






    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                ", bio='" + getBio() + '\'' +
                '}';
    }
}
