package com.polaris.dto;

/**
 * Polaris 
 * 
 * @author Tanisha Ravindran
 * @version 0.0.1
 */

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Response sent back after successful login or registration - includes JWT token and user info

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    
    // JWT token for authenticated requests
    private String token;
    
    // User's unique ID
    private UUID id;
    
    // User's name
    private String name;
    
    // User's email
    private String email;
}