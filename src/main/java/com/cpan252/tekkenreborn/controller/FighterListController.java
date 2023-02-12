package com.cpan252.tekkenreborn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpan252.tekkenreborn.repository.FighterRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/fighterlist")
public class FighterListController {

    private FighterRepository fighterRepository;

    public FighterListController(FighterRepository fighterRepository) {
        this.fighterRepository = fighterRepository;

    }

    @GetMapping
    public String fighterList() {
        return "fighterlist";
    }

    @ModelAttribute
    public void fighters(Model model) {
        model.addAttribute("fighters", fighterRepository.findAll());
    }

}
