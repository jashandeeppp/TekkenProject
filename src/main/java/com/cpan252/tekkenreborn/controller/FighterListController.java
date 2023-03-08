package com.cpan252.tekkenreborn.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpan252.tekkenreborn.model.dto.FigtherSearchByDateDto;
import com.cpan252.tekkenreborn.repository.FighterRepository;
import com.cpan252.tekkenreborn.repository.FighterRepositoryPaginated;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/fighterlist")
public class FighterListController {
    private FighterRepository fighterRepository;
    private FighterRepositoryPaginated fighterRepositoryPaginated;

    private static final int PAGE_SIZE = 5;

    public FighterListController(FighterRepository fighterRepository, FighterRepositoryPaginated fighterRepositoryPaginated) {
        this.fighterRepository = fighterRepository;
        this.fighterRepositoryPaginated = fighterRepositoryPaginated;
    }

    @GetMapping
    public String showFighters(Model model) {
        return "fighterlist";
    }

    /*
     * This method will allow us to populate the model with initial fighter detials
     * 1. We will use the fighterRepositoryPaginated to retrieve the first page of fighters(we set the page size of 5).. We have used fighterRepositoryPaginated .find all method that accept Pageable.ofSize of 5
     * Then we have currentPage which is showing the current number of page
     * then we have totalPages which gets the number of total pages.
     */
    @ModelAttribute
    public void fighters(Model model) {
        var fighterPage = fighterRepositoryPaginated.findAll(PageRequest.of(0, PAGE_SIZE));          // it returns page of fighters
        model.addAttribute("fighters", fighterPage.getContent());
        model.addAttribute("currentPage", fighterPage.getNumber());
        model.addAttribute("totalPages", fighterPage.getTotalPages());
        // model.addAttribute("fighters", fighterRepository.findAll());
        // in this line the first parameter is the attribute name which we will use in html file which is fighters, and the second parameter is the object.
    }

    @ModelAttribute
    public void fightersByDateDto(Model model) {
        model.addAttribute("fightersByDateDto", new FigtherSearchByDateDto());
    }

    // @ModelAttribute in parameter is because we provided empty fightersByDateDto like example fightersByDateDto is a container and before we visit the page we are saying here is the empty container
    // Then we go to fighterlist.html and using the form we fill and we press submit and based on the form values we actually filter the fighters so we show only fighters that are required by the conditions that we provided in the requirements.
    // We are filling this container fightersByDateDto which is the attribute name for the object of FigtherSearchByDateDto

    /*
     * We use fightersByDateDto model attribute from fighterlist.html which is the attribute name for the empty container which we defined above.
     * Then, we fill the form we provide all the data and then we search by name and date.
     * 
     */
    @PostMapping
    public String searchFightersByDate(@ModelAttribute FigtherSearchByDateDto fightersByDateDto,
            Model model) {
        var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        model.addAttribute("fighters", fighterRepository.findByNameStartsWithAndCreatedAtBetween(
                fightersByDateDto.getName(), LocalDate.parse(fightersByDateDto.getStartDate(), dateFormatter),
                LocalDate.parse(fightersByDateDto.getEndDate(), dateFormatter)));
        return "fighterlist";
    }

    @GetMapping("/switchPage")
    public String switchPage(Model model,
         @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch) {
        var page = pageToSwitch.orElse(0);
        var totalPages = (int) model.getAttribute("totalPages");
        if (page < 0 || page > totalPages){
            return "fighterlist";
        }
        var fighterPage = fighterRepositoryPaginated.findAll(PageRequest.of(pageToSwitch.orElse(0), 
        PAGE_SIZE));
        model.addAttribute("fighters", fighterPage.getContent());
        model.addAttribute("currentPage",fighterPage.getNumber());
        return "fighterlist";
    }

}
