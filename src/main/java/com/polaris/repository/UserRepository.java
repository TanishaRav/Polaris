package com.polaris.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polaris.model.User;

// Repository for accessing User data in the database 

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
    // Finding a user by their email address
    Optional<User> findByEmail(String email);
    
    // Checking if a user with this email already exists
    boolean existsByEmail(String email);
}