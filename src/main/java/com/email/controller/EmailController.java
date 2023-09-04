package com.email.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.email.DT.AppointmentDTO;
import com.email.entity.EmailRequest;
import com.email.service.EmailService;
import jakarta.servlet.http.HttpSession;

/**
 * The controller class for the OneHealth Email Service application.
 *
 * This class handles various endpoints related to email functionality.
 * It uses the EmailService to send emails and includes endpoints for sending OTPs and appointment emails.
 * 
 * Note: Make sure to import the required Spring Boot annotations and classes.
 * 
 * @author Madhavi
 * @version 1.0
 */
@RestController
@RequestMapping("/emailService")
public class EmailController {

    @Autowired
    private EmailService emailservice;

    // Endpoint to welcome message
    @GetMapping("/welcome")
    public String welcome() {
        return "Hello, this is my Email API";
    }

    // Map to store OTPs and corresponding email addresses
    private Map<String, Integer> otpMap = new HashMap<>();

    // Endpoint to open the email form for forgot password
    @GetMapping("/forgot")
    public String openEmailForm() {
        return "Forgot email form";
    }

    // Endpoint to send an OTP email
    @PostMapping("/sendotp")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
        try {
            // Generating OTP of 6 digits
            Random random = new Random();
            int otp = random.nextInt(900000) + 100000;
            System.out.println("OTP: " + otp);

            String subject = "OTP from OneHealth Infobell";
            String message_otp = " " + otp;
            String to = request.getTo();

            boolean result = this.emailservice.sendEmail(subject, message_otp, to);

            if (result) {
                // Storing OTP in the map
                System.out.println(result);
                otpMap.put(to, otp);
                return ResponseEntity.ok("OTP sent successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("OTP not sent.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while sending OTP.");
        }
    }

    // Endpoint to send appointment emails
    @PostMapping("/appointmentEmail")
    public void sendAppointmentEmail(@RequestBody AppointmentDTO appointmentDTO) {
        System.out.println("Inside Send Email For Appointment Booking !!");
        System.out.println(appointmentDTO);
        emailservice.sendDoctorAppointmentEmail(appointmentDTO);
        emailservice.sendPatientAppointmentEmail(appointmentDTO);
    }
}
