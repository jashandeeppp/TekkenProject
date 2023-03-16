package com.cpan252.tekkenreborn.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpan252.tekkenreborn.model.form.RegistrationForm;
import com.cpan252.tekkenreborn.repository.UserRepository;


@Controller
@RequestMapping("/register")
public class RegistrationController {
    /*
     * Essentially we have UserRepository and PasswordEncoder
     * PasswordEncoder comes from SecurityConfig.java
     */
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    
    /*
     * Here, we inject UserRepository and PasswordEncoder
     */
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    // Provided mapping for the view
    @GetMapping
    public String showRegistrationForm(){
        return "register";
    }


    // implemented registeredUserAccount capabiliites which implement RegistrationForm and save user.
    @PostMapping
    public String registerUserAccount(RegistrationForm form) {
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
