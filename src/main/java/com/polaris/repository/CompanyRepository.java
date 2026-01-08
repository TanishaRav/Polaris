package com.polaris.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polaris.model.Company;

// Repository for accessing Company data in the database

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    
    // Finding all the companies belonging to a specific user
    List<Company> findByUserId(UUID userId);
    
    // Finding a company by the name for a specific user
    Optional<Company> findByUserIdAndName(UUID userId, String name);
}