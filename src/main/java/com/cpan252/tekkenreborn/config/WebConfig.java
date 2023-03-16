package com.cpan252.tekkenreborn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    // This is for creating custom login page.
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login");
    }
}
