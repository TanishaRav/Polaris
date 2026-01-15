package com.polaris.service;

/**
 * Polaris 
 * 
 * @author Tanisha Ravindran
 * @version 0.0.1
 */

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.polaris.dto.ApplicationRequest;
import com.polaris.dto.ApplicationResponse;
import com.polaris.exception.ResourceNotFoundException;
import com.polaris.model.Application;
import com.polaris.model.Company;
import com.polaris.model.User;
import com.polaris.repository.ApplicationRepository;
import com.polaris.repository.UserRepository;

// Handles all application-related operations - create, read, update, delete, filter

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyService companyService;

    // Get current user from authentication
    private User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    // Convert Application entity to ApplicationResponse DTO
    private ApplicationResponse convertToResponse(Application application) {
        Company company = companyService.getCompanyById(application.getCompanyId());
        
        ApplicationResponse response = new ApplicationResponse();
        response.setId(application.getId());
        response.setCompanyId(application.getCompanyId());
        response.setCompanyName(company.getName());
        response.setRoleTitle(application.getRoleTitle());
        response.setLocationType(application.getLocationType());
        response.setLocationText(application.getLocationText());
        response.setPayMin(application.getPayMin());
        response.setPayMax(application.getPayMax());
        response.setStatus(application.getStatus());
        response.setInterviewType(application.getInterviewType());
        response.setJobPostingUrl(application.getJobPostingUrl());
        response.setNotes(application.getNotes());
        response.setAppliedDate(application.getAppliedDate());
        response.setLastUpdated(application.getLastUpdated());
        response.setCreatedAt(application.getCreatedAt());
        
        return response;
    }

    // Create new application
    public ApplicationResponse createApplication(ApplicationRequest request, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        
        // Find or create company
        Company company = companyService.findOrCreateCompany(request.getCompanyName(), authentication);
        
        // Create new application
        Application application = new Application();
        application.setUserId(currentUser.getId());
        application.setCompanyId(company.getId());
        application.setRoleTitle(request.getRoleTitle());
        application.setLocationType(request.getLocationType());
        application.setLocationText(request.getLocationText());
        application.setPayMin(request.getPayMin());
        application.setPayMax(request.getPayMax());
        application.setStatus(request.getStatus());
        application.setInterviewType(request.getInterviewType());
        application.setJobPostingUrl(request.getJobPostingUrl());
        application.setNotes(request.getNotes());
        application.setAppliedDate(request.getAppliedDate());
        
        Application savedApplication = applicationRepository.save(application);
        return convertToResponse(savedApplication);
    }

    // Get all applications for current user
    public List<ApplicationResponse> getAllApplications(Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        List<Application> applications = applicationRepository.findByUserId(currentUser.getId());
        
        return applications.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Get specific application by ID
    public ApplicationResponse getApplicationById(UUID applicationId, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application", "id", applicationId));
        
        // Verify application belongs to current user
        if (!application.getUserId().equals(currentUser.getId())) {
            throw new RuntimeException("Access denied");
        }
        
        return convertToResponse(application);
    }

    // Update existing application
    public ApplicationResponse updateApplication(UUID applicationId, ApplicationRequest request, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application", "id", applicationId));
        
        // Verify application belongs to current user
        if (!application.getUserId().equals(currentUser.getId())) {
            throw new RuntimeException("Access denied");
        }
        
        // Find or create company if name changed
        Company company = companyService.findOrCreateCompany(request.getCompanyName(), authentication);
        
        // Update fields
        application.setCompanyId(company.getId());
        application.setRoleTitle(request.getRoleTitle());
        application.setLocationType(request.getLocationType());
        application.setLocationText(request.getLocationText());
        application.setPayMin(request.getPayMin());
        application.setPayMax(request.getPayMax());
        application.setStatus(request.getStatus());
        application.setInterviewType(request.getInterviewType());
        application.setJobPostingUrl(request.getJobPostingUrl());
        application.setNotes(request.getNotes());
        application.setAppliedDate(request.getAppliedDate());
        
        Application updatedApplication = applicationRepository.save(application);
        return convertToResponse(updatedApplication);
    }

    // Delete application
    public void deleteApplication(UUID applicationId, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application", "id", applicationId));
        
        // Verify application belongs to current user
        if (!application.getUserId().equals(currentUser.getId())) {
            throw new RuntimeException("Access denied");
        }
        
        applicationRepository.delete(application);
    }

    // Get applications by company
    public List<ApplicationResponse> getApplicationsByCompany(UUID companyId, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        
        List<Application> applications = applicationRepository.findByCompanyId(companyId);
        
        return applications.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Get applications by status
    public List<ApplicationResponse> getApplicationsByStatus(Application.ApplicationStatus status, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        
        List<Application> applications = applicationRepository.findByUserIdAndStatus(currentUser.getId(), status);
        
        return applications.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
}