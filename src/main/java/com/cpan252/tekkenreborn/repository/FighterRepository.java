package com.cpan252.tekkenreborn.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cpan252.tekkenreborn.model.Fighter;

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

public interface FighterRepository extends CrudRepository<Fighter,Long> {

}

