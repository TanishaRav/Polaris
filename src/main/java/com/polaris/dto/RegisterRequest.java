package com.polaris.dto;

/**
 * Polaris 
 * 
 * @author Tanisha Ravindran
 * @version 0.0.1
 */

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This class represents the data we receive when a new user registers

@Data  
@NoArgsConstructor  
@AllArgsConstructor  
public class RegisterRequest {
    
    // User's full name
    @NotBlank(message = "Name is required")
    private String name;
    
    // User's email
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    // User's password
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}