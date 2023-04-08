package com.cpan252.tekkenreborn.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.cpan252.tekkenreborn.model.dto.TekkenUserDto;
// This page is used for fetching the users from the other app.
@Controller
@RequestMapping("/userdashboard")
@CrossOrigin(origins = "http://localhost:8081")     // Usually there is a protection that protects the request from different origin i.e. URL so the user doesn't consume your data from the different url and its like a security thing but it will help us and by mentioning URL in it we say that this URL is trusted and we can consume data from here.
public class UserDashboardController {
    private RestTemplate restTemplate;

    public UserDashboardController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String dashboard(){
        return "userdashboard";
    }

    @ModelAttribute("users")
    public List<TekkenUserDto> getUsers(){
        var users = restTemplate.getForObject("http://localhost:8081/api/userdashboard", TekkenUserDto[].class);
        return Arrays.asList(users);
    }
}
