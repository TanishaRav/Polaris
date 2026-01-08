package com.polaris.model;

/**
 * Polaris 
 * 
 * @author Tanisha Ravindran
 * @version 0.0.1
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    
    @Column(name = "company_id", nullable = false)
    private UUID companyId;
    
    @Column(name = "role_title", nullable = false)
    private String roleTitle;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "location_type")
    private LocationType locationType;
    
    @Column(name = "location_text")
    private String locationText;
    
    @Column(name = "pay_min")
    private Integer payMin;
    
    @Column(name = "pay_max")
    private Integer payMax;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "interview_type")
    private InterviewType interviewType;
    
    @Column(name = "job_posting_url")
    private String jobPostingUrl;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "applied_date")
    private LocalDate appliedDate;
    
    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    // Enums
    public enum LocationType {
        REMOTE, HYBRID, ONSITE
    }
    
    public enum ApplicationStatus {
        DRAFT, APPLIED, OA, INTERVIEW, OFFER, REJECTED, WITHDRAWN
    }
    
    public enum InterviewType {
        PHONE, VIDEO, RECORDED_VIDEO, IN_PERSON
    }
}