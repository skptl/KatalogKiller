package edu.nyu.cs9033.configurations;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailConfig {

	public static void sendMail(String to, String cc, String subject,
			String body) {

		final String username = "UNAME";
		final String password = "PASSWD";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Shilpan Patel <shilpan@nyu.edu>"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setRecipients(Message.RecipientType.CC,
					InternetAddress.parse(cc));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

			System.out.println("Mail sent.");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
