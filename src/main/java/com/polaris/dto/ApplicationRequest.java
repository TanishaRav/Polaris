package com.polaris.dto;

/**
 * Polaris 
 * 
 * @author Tanisha Ravindran
 * @version 0.0.1
 */


import java.time.LocalDate;

import com.polaris.model.Application.ApplicationStatus;
import com.polaris.model.Application.InterviewType;
import com.polaris.model.Application.LocationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data we receive when creating or updating a job application

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {
    
    // Company name 
    @NotBlank(message = "Company name is required")
    private String companyName;
    
    // Job role/position title 
    @NotBlank(message = "Role title is required")
    private String roleTitle;
    
    // Type of work location 
    private LocationType locationType;
    
    // Specific location text 
    private String locationText;
    
    // Minimum salary/pay
    private Integer payMin;
    
    // Maximum salary/pay
    private Integer payMax;
    
    // Application status 
    @NotNull(message = "Status is required")
    private ApplicationStatus status;
    
    // Type of interview if status is INTERVIEW
    private InterviewType interviewType;
    
    // Link to job posting
    private String jobPostingUrl;
    
    // Additional notes
    private String notes;
    
    // Date when application was submitted
    private LocalDate appliedDate;
}