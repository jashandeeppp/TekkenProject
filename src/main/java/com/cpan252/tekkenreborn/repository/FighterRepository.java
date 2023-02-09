package com.cpan252.tekkenreborn.repository;

import java.util.Optional;

import com.cpan252.tekkenreborn.model.Fighter;

// It will be an interface that defines operations with the fighter table in the database.
// In this interface we have all the methods we need to interact with the database.
public interface FighterRepository {
    Iterable<Fighter> findAll();  // Iterable is just an interface in java that is used to define entities that can be itterated like arrays, list etc. 
    Optional<Fighter> findById(Long id);
    void save(Fighter fighter);
}
