package com.glecom.handlers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author navneet
 * implements mail sending functionality from java and gmail.
 */
public class MailSender {
	private static final String from = "gmail-id-here@gmail.com";
	private final static String host = "smtp.gmail.com";
	private static final String password = "gmail-password-here";
	/**
	 * @param to
	 * @param subject
	 * @param text
	 * to - recipeints address
	 * subject - subject of email
	 * text - body of email
	 */
	public static void SendMail(String to, String subject, String text) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",  
	            "javax.net.ssl.SSLSocketFactory");  
		properties.put("mail.smtp.auth", "true");  
		properties.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(properties,  
				   new javax.mail.Authenticator() {  
				   protected PasswordAuthentication getPasswordAuthentication() {  
				   return new PasswordAuthentication(from,password);  
				   }  
				  });  
		try {  
			   MimeMessage message = new MimeMessage(session);  
			   message.setFrom(new InternetAddress(from));
			   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
			   message.setSubject(subject);  
			   message.setText(text);  
			   Transport.send(message);  
			  } catch (MessagingException e) {throw new RuntimeException(e);}
	}
	
}
