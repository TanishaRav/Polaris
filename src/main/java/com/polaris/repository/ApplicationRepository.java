package com.polaris.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polaris.model.Application;
import com.polaris.model.Application.ApplicationStatus;

// Repository for accessing Application data in the database

@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    
    // Finding all the applications belonging to a specific user
    List<Application> findByUserId(UUID userId);
    
    // Finding all the applications for a specific company
    List<Application> findByCompanyId(UUID companyId);
    
    // Finding all the applications by user and status
    List<Application> findByUserIdAndStatus(UUID userId, ApplicationStatus status);
    
    // Finding all the applications by user, ordered by applied date 
    List<Application> findByUserIdOrderByAppliedDateDesc(UUID userId);
}