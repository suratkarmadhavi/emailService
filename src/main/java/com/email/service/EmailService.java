package com.email.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.email.DT.AppointmentDTO;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;




@Service
public class EmailService {
	
	    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EmailService.class);
	   
	    public boolean sendEmail(String subject, String otp, String to) throws IOException {
        // Variable for Gmail
        String from = "onehealthinfobell@gmail.com";
        String host = "smtp.gmail.com";
        boolean f = false;
        System.out.println("the otp from the service class ");
        System.out.println(otp);
        String userName = "onehealthinfobell@gmail.com";
        String password = "njepyniemgzrybpi";

        // Get the system property
        Properties properties = System.getProperties();
        System.out.print("PROPERTIES " + properties);

        String templateFileName = "email.html";

        String htmlContent = readHtmlFromTemplate(templateFileName);
        

        htmlContent = htmlContent.replace("{{otp}}",String.valueOf(otp));

 
        // Set host
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Step 1: Get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        session.setDebug(true);

        // Step 2: Compose the message [text, multimedia]
        MimeMessage m = new MimeMessage(session);
//        MimeMessageHelper mime=new MimeMessageHelper(m , true);
        
        
        try {
            // From mail
            m.setFrom(from);

            // Adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Adding subject to message
            m.setSubject(subject);

            // Adding text to message
//            m.setText(message);
            m.setContent(htmlContent, "text/html");
            // Step 3: Send the message using the Transport class
            Transport.send(m);

            System.out.print("Sent Success........!");
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

	    
	   
	  //  sendAppointmentEmail common
//	    public void sendAppointmentEmail(AppointmentDTO appointmentdto) {
//	        System.out.println("Inside Send Email For Appointment Booking !!");
//	        System.out.println(appointmentdto);
//	        
//	        // Sender's email and password
//	        String senderEmail = "onehealthinfobell@gmail.com"; // Replace with your sender email
//	        String senderPassword = "njepyniemgzrybpi"; // Replace with your sender email password
//
//	        // Recipient's email
//	        String recipientEmail = appointmentdto.getPatient_email(); // Assuming appointment has a patient email field
//	        System.out.println(recipientEmail);
//	        String doctorEmail = appointmentdto.getDoctor_email();
//	        System.out.println(doctorEmail);
//	        // Email properties
//	        Properties props = new Properties();
//	        props.put("mail.smtp.auth", "true");
//	        props.put("mail.smtp.starttls.enable", "true");
//	        props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP host
//	        props.put("mail.smtp.port", "587"); // Replace with your SMTP port
//
//	        // Create a session with the email credentials
//	        Session session = Session.getInstance(props, new Authenticator() {
//	            @Override
//	            protected PasswordAuthentication getPasswordAuthentication() {
//	                return new PasswordAuthentication(senderEmail, senderPassword);
//	            }
//	        });
//
//	        try {
//	            // Create a MimeMessage object
//	            MimeMessage message = new MimeMessage(session);
//
//	            // Set the sender and recipient addresses
//	            message.setFrom(new InternetAddress(senderEmail));
//	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
//	            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(doctorEmail));
//
//	            // Set the email subject and content type
//	            message.setSubject("Appointment Confirmation");
//
//	            // Get the HTML content from a template
//	            String htmlContent = readHtmlFromTemplate("appointment.html");
//	            
//	            // Replace placeholders in HTML content
//
//	        	            htmlContent = htmlContent.replace("{{patient_name}}", getNonNullString(appointmentdto.getPatient_name()));
//	        	            htmlContent = htmlContent.replace("{{doctor_name}}", getNonNullString(appointmentdto.getDoctor_name()));
//	        	            htmlContent = htmlContent.replace("{{contact}}", getNonNullString(appointmentdto.getContact()));
//	        	            htmlContent = htmlContent.replace("{{description}}", getNonNullString(appointmentdto.getDescription()));
//	        	            htmlContent = htmlContent.replace("{{appointment_date}}", getNonNullString(appointmentdto.getDate()));
//	        	            htmlContent = htmlContent.replace("{{appointment_time}}", getNonNullString(appointmentdto.getAppointmentTime()));
//	        	            htmlContent = htmlContent.replace("{{address}}", getNonNullString(appointmentdto.getAddress()));
//	            // Set the HTML content of the message
//	            message.setContent(htmlContent, "text/html");
//
//	            // Send the email
//	            Transport.send(message);
//
//	            LOGGER.info("Email sent to: {}", recipientEmail);
//	        } catch (MessagingException e) {
//	            LOGGER.error("Error sending email: {}", e.getMessage());
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//
//	    }

	    
	    
	//sendDoctorAppointmentEmail   
	    
	    public void sendDoctorAppointmentEmail(AppointmentDTO appointmentdto) {
	        System.out.println("Inside Send Email For Appointment Booking !!");
	        System.out.println(appointmentdto);
	        
	        // Sender's email and password
	        String senderEmail = "onehealthinfobell@gmail.com"; // Replace with your sender email
	        String senderPassword = "njepyniemgzrybpi"; // Replace with your sender email password

	        String doctorEmail = appointmentdto.getDoctor_email();
	        System.out.println(doctorEmail);
	        // Email properties
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP host
	        props.put("mail.smtp.port", "587"); // Replace with your SMTP port

	        // Create a session with the email credentials
	        Session session = Session.getInstance(props, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(senderEmail, senderPassword);
	            }
	        });

	        try {
	            // Create a MimeMessage object
	            MimeMessage message = new MimeMessage(session);

	            // Set the sender and recipient addresses
	            message.setFrom(new InternetAddress(senderEmail));
	         
	            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(doctorEmail));

	            // Set the email subject and content type
	            message.setSubject("Appointment Confirmation with Patient");

	            // Get the HTML content from a template
	            String htmlContent = readHtmlFromTemplate("DoctorAppointment.html");
	            
	            // Replace placeholders in HTML content
	            			htmlContent = htmlContent.replace("{{doctor_name}}", getNonNullString(appointmentdto.getDoctor_name()));
	        	            htmlContent = htmlContent.replace("{{patient_name}}", getNonNullString(appointmentdto.getPatient_name()));
	        	          
	        	            htmlContent = htmlContent.replace("{{contact}}", getNonNullString(appointmentdto.getContact()));
	        	            htmlContent = htmlContent.replace("{{description}}", getNonNullString(appointmentdto.getDescription()));
	        	            htmlContent = htmlContent.replace("{{appointment_date}}", getNonNullString(appointmentdto.getDate()));
	        	            htmlContent = htmlContent.replace("{{appointment_time}}", getNonNullString(appointmentdto.getAppointmentTime()));
	        	            htmlContent = htmlContent.replace("{{address}}", getNonNullString(appointmentdto.getAddress()));
	            // Set the HTML content of the message
	            message.setContent(htmlContent, "text/html");

	            // Send the email
	            Transport.send(message);

	            LOGGER.info("Email sent to: {}", doctorEmail);
	        } catch (MessagingException e) {
	            LOGGER.error("Error sending email: {}", e.getMessage());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
	    
	    
	    
	    
//sendPatientAppointmentEmail   
	    
	    public void sendPatientAppointmentEmail(AppointmentDTO appointmentdto) {
	        System.out.println("Inside Send Email For Appointment Booking !!");
	        System.out.println(appointmentdto);
	        
	        // Sender's email and password
	        String senderEmail = "onehealthinfobell@gmail.com"; // Replace with your sender email
	        String senderPassword = "njepyniemgzrybpi"; // Replace with your sender email password

	        String recipientEmail = appointmentdto.getPatient_email(); // Assuming appointment has a patient email field
	        System.out.println(recipientEmail);
	        // Email properties
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP host
	        props.put("mail.smtp.port", "587"); // Replace with your SMTP port

	        // Create a session with the email credentials
	        Session session = Session.getInstance(props, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(senderEmail, senderPassword);
	            }
	        });

	        try {
	            // Create a MimeMessage object
	            MimeMessage message = new MimeMessage(session);

	            // Set the sender and recipient addresses
	            message.setFrom(new InternetAddress(senderEmail));
	         
	            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

	            // Set the email subject and content type
	            message.setSubject("Appointment Confirmation with Patient");

	            // Get the HTML content from a template
	            String htmlContent = readHtmlFromTemplate("PatientAppointment.html");
	            
	            // Replace placeholders in HTML content
	            			htmlContent = htmlContent.replace("{{doctor_name}}", getNonNullString(appointmentdto.getDoctor_name()));
	        	            htmlContent = htmlContent.replace("{{patient_name}}", getNonNullString(appointmentdto.getPatient_name()));
	        	          
	        	            htmlContent = htmlContent.replace("{{contact}}", getNonNullString(appointmentdto.getContact()));
	        	            htmlContent = htmlContent.replace("{{description}}", getNonNullString(appointmentdto.getDescription()));
	        	            htmlContent = htmlContent.replace("{{appointment_date}}", getNonNullString(appointmentdto.getDate()));
	        	            htmlContent = htmlContent.replace("{{appointment_time}}", getNonNullString(appointmentdto.getAppointmentTime()));
	        	            htmlContent = htmlContent.replace("{{address}}", getNonNullString(appointmentdto.getAddress()));
	            // Set the HTML content of the message
	            message.setContent(htmlContent, "text/html");

	            // Send the email
	            Transport.send(message);

	            LOGGER.info("Email sent to: {}", recipientEmail);
	        } catch (MessagingException e) {
	            LOGGER.error("Error sending email: {}", e.getMessage());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
	    
	    
	    private String readHtmlFromTemplate(String templateFileName) throws IOException {
	        try (InputStream inputStream = getClass().getResourceAsStream("/templates/" + templateFileName)) {
	            if (inputStream != null) {
	                byte[] contentBytes = inputStream.readAllBytes();
	                return new String(contentBytes, StandardCharsets.UTF_8);
	            } else {
	                throw new IOException("Template file not found: " + templateFileName);
	            }
	        }
	    }


	    
	    private String getNonNullString(Object value) {
	        return (value != null) ? value.toString() : "";
	    }

}



