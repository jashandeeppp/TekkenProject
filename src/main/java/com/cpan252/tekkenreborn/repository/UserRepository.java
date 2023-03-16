package com.cpan252.tekkenreborn.repository;

import org.springframework.data.repository.CrudRepository;

import com.cpan252.tekkenreborn.model.User;

// It act as the heart for the User operations.
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
