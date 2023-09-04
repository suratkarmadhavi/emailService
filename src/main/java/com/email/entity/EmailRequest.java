package com.email.entity; // Package declaration

/**
 * Entity class representing an email request.
 * This class is used to store email details.
 * 
 * @author Madhavi
 * @version 1.0
 */
public class EmailRequest {

	// Private member variables to store email details
	private String to; // Email recipient
	private String subject; // Email subject
	private String message; // Email message content
	
	// Default constructor for EmailRequest class.
	public EmailRequest() {
		super();
	}

	// Parameterized constructor to initialize email details
	public EmailRequest(String to, String subject, String message) {
		super();
		this.to = to; // Set email recipient
		this.subject = subject; // Set email subject
		this.message = message; // Set email message content
	}

	// Getter method for retrieving the email recipient
	public String getTo() {
		return to;
	}

	// Setter method for setting the email recipient
	public void setTo(String to) {
		this.to = to;
	}

	// Getter method for retrieving the email subject
	public String getSubject() {
		return subject;
	}

	// Setter method for setting the email subject
	public void setSubject(String subject) {
		this.subject = subject;
	}

	// Getter method for retrieving the email message content
	public String getMessage() {
		return message;
	}

	// Setter method for setting the email message content
	public void setMessage(String message) {
		this.message = message;
	}

	// Override toString method to provide a string representation of the object
	@Override
	public String toString() {
		return "EmailRequest [to=" + to + ", subject=" + subject + ", message=" + message + "]";
	}
}
