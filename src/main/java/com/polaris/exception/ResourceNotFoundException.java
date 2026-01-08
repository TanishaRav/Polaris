package com.polaris.exception;

/**
 * Polaris 
 * 
 * @author Tanisha Ravindran
 * @version 0.0.1
 */

// Custom exception thrown when a requested resource is not found

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
    }
}