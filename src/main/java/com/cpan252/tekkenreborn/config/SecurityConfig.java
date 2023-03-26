package com.cpan252.tekkenreborn.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


import com.cpan252.tekkenreborn.model.User;
import com.cpan252.tekkenreborn.repository.UserRepository;



@Configuration
/*
 * Configurtation class for Spring is like a holder of beans.
 * We can use this class to define beans that we want to use in our application.
 */
@EnableMethodSecurity
public class SecurityConfig {
    
    /*
     * We need PasswordEncoder bean using BCryptPasswordEncoder implementation(Applies bcrypt string hashing encryption).
     * We used encoder to apply password encryption in the database, so user password is never stored as decrypted and gets
     * encrypted when user logs in and gets compared with encrypted password stored in the database.
     */
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
     * We implemented userDetailsService and UserRepository
     * So, esentailly we try to find user by findByUsername using username
     * If user is not null then we return user and if it is null then we will give UsernameNotFoundException. 
     */
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException(username);
        };
    }

    @Bean
    // Here in this we implemented some security chain.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /*
         * WE authorize HTTP request to H2 console and we permit all the request.
         * We also secured our design and fighterlist and can only see by the people who has the role user
         * And we are making sure that the login page is on /login.
         */
        return http
            //We are authorizing HTTP Request. So for the design page and fighterlist page we are allowing anyone with role user to see the page.
            .authorizeHttpRequests()
            .requestMatchers(toH2Console()).permitAll()
            .requestMatchers("/design","/fighterlist")
            .hasRole("USER")
            .anyRequest().permitAll()

            // So we define login page with /login
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/design",true)

            // And When we logout, we logout to / url
            .and()
            .logout()
            .logoutSuccessUrl("/")


            // Make H2-Console non-secured; for debug purposes.
            // And we have to ignore some of the request matchers specifically h2-console, so we don't need to login to h2 which is for the dev purposes. 
            .and()
            .csrf() // its one of the web security concepts. It stands for Crosss Site Request Forgery is an attack that forces authenticated users to submit a request to a Web application against which they are currently authenticated. So basically it offers protection
            .ignoringRequestMatchers(toH2Console()) // we are igonring csrf protection for H2 console.


            // Allow pages to be loaded in frames from the same origin: needed for H2-console.
            .and()
            .headers()
            .frameOptions()
            .sameOrigin()

            .and()
            .build();
    }
}
