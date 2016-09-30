package com.formsdirectinc.qa.app.registration.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailSender {

	public static void sendmail(String msg, String subject,String emailConfigproperties) {
		
		if (emailConfigproperties.isEmpty())
			emailConfigproperties="emailConfiguration";
		
		try {
		
			Properties prop=new Properties();
			prop.load(new FileInputStream(new File(emailConfigproperties)));
		
		Session session = Session.getDefaultInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								prop.getProperty("senderEmail"), prop.getProperty("SenderPassword"));
					}
				});
		
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(prop.getProperty("senderEmail")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					prop.getProperty("receiverEmail")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					prop.getProperty("receiverEmailCc")));
			message.setSubject("Alert on" + subject);
			message.setText("Automatically generated mail on script check, kindly do not reply \n "
					+ msg);

			Transport.send(message);
			System.out.println("message sent successfully");

		} catch (MessagingException|IOException e) {
			e.printStackTrace();
		}

	}
	
}
