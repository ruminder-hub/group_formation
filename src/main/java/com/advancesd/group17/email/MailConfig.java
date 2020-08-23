package com.advancesd.group17.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailConfig {

	public void sendmail(String mail, String banner, String password) throws AddressException, MessagingException, IOException {
		   
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("advsdcgrp17@gmail.com", "grp17@2020");
		      }
		   });
		   
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("advsdcgrp17@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
		   msg.setSubject("Password Recovery");
		   msg.setContent("Your BannerId is : "+banner+ "\nYour Password is : "+password, "text/html");

		   Transport.send(msg);   
		}
}
