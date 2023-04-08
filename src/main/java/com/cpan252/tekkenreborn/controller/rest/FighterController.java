// package com.cpan252.tekkenreborn.controller.rest;

// import java.util.Optional;

// import org.springframework.data.domain.PageRequest;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;

// import com.cpan252.tekkenreborn.model.Fighter;
// import com.cpan252.tekkenreborn.model.dto.CreateFighter;
// import com.cpan252.tekkenreborn.repository.FighterRepository;
// import com.cpan252.tekkenreborn.repository.FighterRepositoryPaginated;

// import jakarta.validation.Valid;
// import jakarta.websocket.server.PathParam;

// /*
//  * REst controller that we used in the last class is more responsible for providing data to the other application.
//  * So esentially it acts as a dataprovider for other UIs. We can build our UI in REact, Angular, Vew.
//  * It provides JSON data which can be used by any frameworks.
//  * WE provided GET, POST, PUT, DELETE which are CRUD operations.
//  */
// @RestController     
// // @Controller      we can also use these both annotation instead of @RestController.
// // @ResponseBody
// // Esentially the full path is http://localhost:8080/api/fighters
// @RequestMapping(path = "/api/fighters", produces = "application/json")  // here we tell about json.
// /*
//  * We have used our Repository one that returns paginated fighters i.e. FighterRepositoryPaginated and the other that returns normal fighters i.e. FighterRepository
//  * and we created controller.
//  * if we provide page and size we return page request means fighterRepositoryPaginated and if not then we try to list all the fighters.
//  */
// public class FighterController {
    
//     private final FighterRepositoryPaginated fighterRepositoryPaginated;
//     private final FighterRepository fighterRepository;

//     public FighterController(FighterRepositoryPaginated fighterRepositoryPaginated, FighterRepository fighterRepository){
//         this.fighterRepositoryPaginated = fighterRepositoryPaginated;
//         this.fighterRepository = fighterRepository;
//     }

//     /*
//      * We have these two repositories available when we provide page and size it will return fighters from fighterRepository
//      * on the other hand when we don't provide page and size we will use PageRequest and we will return only fighters from FighterRepositoryPaginated.
//      * In the parameter we have used Optional just to be on safe side of checking and it is more descriptive like .isPresent and .get().
//      */
//     @GetMapping
//     public Iterable<Fighter> allFighters(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
//         if (!page.isPresent() || !size.isPresent()){
//             return fighterRepository.findAll(); 
//         } else {
//             return fighterRepositoryPaginated.findAll(PageRequest.of(page.get(), size.get()));
//         }
//     }

//     /*
//      * We implemented delete request, so we are deleting fighter by id.
//      */
//     @DeleteMapping("/{id}")
//     public void deleteFighter(@PathVariable("id") Long id){
//         fighterRepository.deleteById(id);
//     }

//     /*
//      * We have to create a Fighter Dto. For ex. we don't have id for fighter and id is autogenereated by the database in Java
//      * we can only use the fields which we want to use but to create the proper fighter and its not a good practice to use a ond fighter object which we aleady used in our dto.
//      */
//     @PostMapping
//     public Fighter createFighter(@Valid @RequestBody CreateFighter fighter) {
//         return fighterRepository.save(fighter.toFighter());
//     }

//     @PutMapping("/{id}")
//     public Fighter updateFighter(@PathVariable("id") Long id,
//             @Valid @RequestBody CreateFighter fighter) {
//         var fighterToUpdate = fighterRepository.findById(id).orElseThrow();
//         fighterToUpdate.setName(fighter.getName());
//         fighterToUpdate.setAnimeFrom(fighter.getAnimeFrom());
//         fighterToUpdate.setDamagePerHit(fighter.getDamagePerHit());
//         fighterToUpdate.setHealth(fighter.getHealth());
//         fighterToUpdate.setResistance(fighter.getResistance());
//         return fighterRepository.save(fighterToUpdate);
//     }  
// }
