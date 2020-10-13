package com.mdomeck.codefellowship.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private HomeController homeController;

    @Test
    public void contextLoads(){
        assertThat(homeController).isNotNull();
    }

    @Test
    public void homeShouldReturnLoginLink(){
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("<a href=\"/login\">Click to Login</a>");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("<a href=\"/signup\">Click to Sign-up</a>");
    }

}
