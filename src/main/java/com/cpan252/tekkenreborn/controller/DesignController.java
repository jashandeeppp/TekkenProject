package com.cpan252.tekkenreborn.controller;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui. Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cpan252.tekkenreborn.model.Fighter;
// import com.cpan252.tekkenreborn.model.FighterPool;
import com.cpan252.tekkenreborn.model.Fighter.Anime;
import com.cpan252.tekkenreborn.repository.impl.JdbcFighterRepository;

import jakarta.validation.Valid;
// import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller 
@Slf4j    //logger  -- it helps in generating log property which we have used in code.
// @SessionAttributes("fighterPool")  //**** this indicates that fighterpool has a lifetime of a session.
// To display the fighterpool and to display it through it different pages we have to increase the lifetime of a session and it is done by this 
// if we don't use it the session will terminate quickly.
@RequestMapping("/design")
public class DesignController {

    @Autowired // this annotation injects JdbcFighterRespository (bean) to the other bean.
    private JdbcFighterRepository fighterRepository;

    /*
     * We used get mapping annotation to map the design method to get request.
     * When we have getMapping it directs us to design method which returns String which points to the template.
     */
    @GetMapping
    public String design(){
        return "design";
    }

    @ModelAttribute
    /*
     * We have annotated animes with ModelAttribute
     */
    public void animes(Model model){       // for passing animes to form.

        /*
         * we converted a list of enums to the list of their titles.
         * map is intermediate stream
         * we collect into list
         */
        /*
         * We have added anime as a model attribute so we can actually get it from the form.
         */
        /*
         * We essentially convert them from a list of animes as a enum to a list of anime title
         */
      
        var animes = EnumSet.allOf(Anime.class);
        /*
         * animes return the list of string.
         */
        /*
         * then we use the list of string into the modal to just providing this value to the form and we can display to the form or any html page
         * Which means here(below) we are connecting Java with HTML.
         */
        model.addAttribute("animes", animes);
        log.info("animes converted to string :{}", animes);
    }

    /******* 
     * Here, we are providing fighter pool model, to add fighter to this model
     * and display it on a different page.
     * @return Fighter Pool model that will last lifetime of a session
     */
//this model here has a session lifetime.

    // @ModelAttribute(name = "fighterPool")
    // public FighterPool fighterPool(){
    //     return new FighterPool();
    // }


    // we created a mew fighter object to be populated from the form inputs.
    /*
     * We have used fighter model that will be used in the form.
     */
    /*********
     * 1. We have created a new Fighter object here, to be populated from the form inputs.
     * 2. We have to reference the Fighter object properties in the form and bind them to the corresponding inputs.
     * 3. We have to submit Form (execute POST request) and make sure Fighter details are valid means adding validation.
     * @return here will return fighter model that we will nedd only for request (form) submission
     */
    // We bind this @ModelAttribute fighter with HTML form.
    @ModelAttribute
    //****  This model attiribute has a lifetime of a request. So essentiolly when we will add fighter it will then disappear. 
    public Fighter fighter(){
        return Fighter
                .builder()
                .build();
    }

    /*
     * We have a post request which will essentially add our fighter to the fighterpool
     * and will reutrn us back to the design page.
     */
    // we added errors object as a parameter,
    @PostMapping
    public String processFighterAddition(@Valid Fighter fighter,  BindingResult result /*@ModelAttribute FighterPool pool*/ ){ 
        if(result.hasErrors()){
            return "design";
        }
        // pool.add(fighter);
        /*
         * When we use our desing page and create a fighter it will save into the database.
         */
        log.info("Processing fighter: {}",fighter);
        fighterRepository.save(fighter);
        log.info("Saved figher with id: {}");
        return "redirect:/design";
    }

}
