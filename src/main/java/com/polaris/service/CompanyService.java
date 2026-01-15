package com.polaris.service;

/**
 * Polaris 
 * 
 * @author Tanisha Ravindran
 * @version 0.0.1
 */

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.polaris.exception.ResourceNotFoundException;
import com.polaris.model.Company;
import com.polaris.model.User;
import com.polaris.repository.CompanyRepository;
import com.polaris.repository.UserRepository;

// Handles company-related operations

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    // Get current user from authentication
    private User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    // Find or create a company by name for the current user
    public Company findOrCreateCompany(String companyName, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        
        // Try to find existing company
        return companyRepository.findByUserIdAndName(currentUser.getId(), companyName)
                .orElseGet(() -> {
                    // Create new company if doesn't exist
                    Company newCompany = new Company();
                    newCompany.setUserId(currentUser.getId());
                    newCompany.setName(companyName);
                    return companyRepository.save(newCompany);
                });
    }

    // Get all companies for current user
    public List<Company> getAllCompanies(Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        return companyRepository.findByUserId(currentUser.getId());
    }

    // Get specific company by ID
    public Company getCompanyById(UUID companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyId));
    }
}