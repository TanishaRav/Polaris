package com.polaris.controller;

/**
 * Polaris 
 * 
 * @author Tanisha Ravindran
 * @version 0.0.1
 */

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polaris.dto.ApplicationRequest;
import com.polaris.dto.ApplicationResponse;
import com.polaris.model.Application;
import com.polaris.service.ApplicationService;

import jakarta.validation.Valid;

// REST API endpoints for managing the job applications

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // POST /api/applications - Creating new applications
    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(
            @Valid @RequestBody ApplicationRequest request,
            Authentication authentication) {
        ApplicationResponse response = applicationService.createApplication(request, authentication);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET /api/applications - Getting all user's applications
    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAllApplications(Authentication authentication) {
        List<ApplicationResponse> applications = applicationService.getAllApplications(authentication);
        return ResponseEntity.ok(applications);
    }

    // GET /api/applications/{id} - Getting a specific application
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(
            @PathVariable UUID id,
            Authentication authentication) {
        ApplicationResponse response = applicationService.getApplicationById(id, authentication);
        return ResponseEntity.ok(response);
    }

    // PUT /api/applications/{id} - Updating the application
    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponse> updateApplication(
            @PathVariable UUID id,
            @Valid @RequestBody ApplicationRequest request,
            Authentication authentication) {
        ApplicationResponse response = applicationService.updateApplication(id, request, authentication);
        return ResponseEntity.ok(response);
    }

    // DELETE /api/applications/{id} - Deleting the application
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable UUID id,
            Authentication authentication) {
        applicationService.deleteApplication(id, authentication);
        return ResponseEntity.noContent().build();
    }

    // GET /api/applications/company/{companyId} - Getting the applications by company
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByCompany(
            @PathVariable UUID companyId,
            Authentication authentication) {
        List<ApplicationResponse> applications = applicationService.getApplicationsByCompany(companyId, authentication);
        return ResponseEntity.ok(applications);
    }

    // GET /api/applications/status/{status} - getting the applications by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByStatus(
            @PathVariable Application.ApplicationStatus status,
            Authentication authentication) {
        List<ApplicationResponse> applications = applicationService.getApplicationsByStatus(status, authentication);
        return ResponseEntity.ok(applications);
    }
}