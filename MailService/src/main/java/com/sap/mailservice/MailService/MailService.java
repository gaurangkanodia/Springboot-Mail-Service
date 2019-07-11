package com.sap.mailservice.MailService;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MailService implements MailServiceInterface
{
	public void mailMethod(MailMessage mail) {
		final String username = "noreplyc4c@gmail.com"; //sender mail id
		final String password = "Thisissampletest";// sender password

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587"); //587 465   

		props.put("proxySet", "true");
		props.put("http.proxyHost", "10.33.130.232");
		props.put("http.proxyPort", "8080");
		props.put("mail.smtp.socketFactory.port", 465);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getMailTo()));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mail.getMailCc()));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(mail.getMailBcc()));
			message.setSubject(mail.getMailSubject());
			message.setText(mail.getMailMessage());
			Transport.send(message);
			System.out.println("Send mail for " + mail.getMailMessage());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
