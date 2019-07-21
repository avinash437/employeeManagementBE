package com.amdocs.Avinash.common.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMailUtility {
	@Autowired
	public Configuration configuration;

	public void sendMail(String subject, String body) {
		String to = "avinash437@gmail.com";// change accordingly
		String from = "hackathonCyprus437@gmail.com";// change accordingly
		String host = "localhost";// or IP address

		// Get the session object
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port: 587", "587");
		properties.put("mail.smtp.starttls.enable", true);
		properties.setProperty("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("hackathonCyprus437@gmail.com", "hackathonCyprus437123$%");																									// PassWord
			}
		});

		// compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(body);

			// Send message
			Transport.send(message, configuration.getPropertyValue("username"),
					configuration.getPropertyValue("password"));
			System.out.println("message sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
