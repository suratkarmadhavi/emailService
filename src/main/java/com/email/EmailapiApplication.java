// Import necessary classes from Spring Boot framework
package com.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The main class to start the OneHealth Email Service application.
 *
 * This class serves as the entry point for the Email Service application.
 * It uses the SpringApplication class to bootstrap and launch the Spring Boot application.
 * 
 * Note: Make sure to import the required Spring Boot annotations.
 * 
 * @author Madhavi
 * @version 1.0
 */
@SpringBootApplication
@RestController
public class EmailapiApplication {

    // Main method, the entry point of the application
    public static void main(String[] args) {
        // Use SpringApplication.run() to start the Spring Boot application for the Email Service.
        SpringApplication.run(EmailapiApplication.class, args);
    }

    // Define a handler for HTTP GET requests
    @GetMapping
    public String getMessage() {
        // Return a simple message as the response when the root path is accessed.
        return "OneHealth - EmailService..!!";
    }
}
