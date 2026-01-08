package com.polaris.dto;

/**
 * Polaris 
 * 
 * @author Tanisha Ravindran
 * @version 0.0.1
 */

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This class represents the data we receive when a user tries to log in - it only requires email and password

@Data  
@NoArgsConstructor  
@AllArgsConstructor  
public class LoginRequest {
    
    // Email field 
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    // Password field 
    @NotBlank(message = "Password is required")
    private String password;
}