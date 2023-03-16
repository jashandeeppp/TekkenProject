package com.cpan252.tekkenreborn.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cpan252.tekkenreborn.model.Fighter;
import com.cpan252.tekkenreborn.model.Fighter.Anime;



// It will be an interface that defines operations with the fighter table in the database.
// In this interface we have all the methods we need to interact with the database.
// public interface FighterRepository {
//     Iterable<Fighter> findAll();  // Iterable is just an interface in java that is used to define entities that can be itterated like arrays, list etc. 
//     Optional<Fighter> findById(Long id);
//     void save(Fighter fighter);
// }


/*
 * By using CrudRepository we don't need to implement CRUD methods and Spring just do it by itself.
 * For example: we are now not implementing any of the above methods which we did earlier but still we can use them
 * These methods are generally same for every entity and spring provides us with CRUD repsoitory interface
 * which will help us to avoid doing same thing again and again and focus on more commplex query that we want to implement.
 */

 /*
  * JPA provides us an option to symentically describe query by name without using real sql query. 
  * So, we start it with find which shows that we are doing SELECT query, then using By we specify that
  * we are going to search using some of the Fighter properties, and after that we provide property name.
  * So, we could use findByname or findBydamagerPerHit etc.
  * By using this findBy convention we can create custom queries 
  */
  /*
   * We esentially implement paging and sorting repository. 
   * For ex. We have 30 fighters
   */
public interface FighterRepository extends CrudRepository<Fighter,Long> {
    List<Fighter> findByAnimeFrom(Anime anime); 
    // We start with findBy to indicate that we are searching by some conditions, then we must provide name prefix and two dates: from and to
    List<Fighter> findByNameStartsWithAndCreatedAtBetween(String name, LocalDate startDate, LocalDate endDate);
}

 