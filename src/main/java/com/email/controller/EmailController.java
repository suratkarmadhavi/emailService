package com.email.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.DT.AppointmentDTO;
import com.email.entity.EmailRequest;
import com.email.service.EmailService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/emailService")

public class EmailController {

	@Autowired
	private EmailService emailservice;

	@GetMapping("/welcome")
	public String welcome() {
		return "Hello this is my Email API";
	}

	// Map to store OTPs and corresponding email addresses
	private Map<String, Integer> otpMap = new HashMap<>();

	@GetMapping("/forgot")
	public String openEmailForm() {
		return "Forgot email form";
	}

	@PostMapping("/sendotp")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request, HttpSession session) {
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

	@PostMapping("/appointmentEmail")
	public void sendAppointmentEmail(@RequestBody AppointmentDTO appointmentDTO) {
		System.out.println("Inside Send Email For Appointment Booking !!");
		System.out.println(appointmentDTO);
		emailservice.sendAppointmentEmail(appointmentDTO);
	}
}
