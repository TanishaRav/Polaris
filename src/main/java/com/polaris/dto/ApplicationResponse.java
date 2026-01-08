package com.polaris.dto;

/**
 * Polaris 
 * 
 * @author Tanisha Ravindran
 * @version 0.0.1
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.polaris.model.Application.ApplicationStatus;
import com.polaris.model.Application.InterviewType;
import com.polaris.model.Application.LocationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Response sent back when retrieving application data - includes all application details plus company info

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponse {
    
    // Application ID
    private UUID id;
    
    // Company ID
    private UUID companyId;
    
    private String companyName;
    
    private String roleTitle;
    
    private LocationType locationType;
    
    private String locationText;
    
    private Integer payMin;
    
    private Integer payMax;
    
    private ApplicationStatus status;
    
    private InterviewType interviewType;
    
    private String jobPostingUrl;
    
    private String notes;
    
    private LocalDate appliedDate;
    
    private LocalDateTime lastUpdated;
    
    private LocalDateTime createdAt;
}