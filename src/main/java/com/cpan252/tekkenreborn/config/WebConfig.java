package com.cpan252.tekkenreborn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    // This is for creating custom login page.
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("authentication/login");    // added controller for pages.
    }


    /*
     * WE create a new REstTemplate bean to use for all requests.
     * It helps us to fetch data from the Tekken User Dashboard API
     * @return RestTemplate.
     */
    // We are gonna use it to get user from the UserDashBoard project 
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    

}
