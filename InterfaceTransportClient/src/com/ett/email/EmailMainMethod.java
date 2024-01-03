package com.ett.email;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.ett.util.BOCCommonUtil;

public class EmailMainMethod {

	public static void main(String args[]) {

		System.out
				.println("*************** Entering into Email new Notification Client  **************");

		String mastRefnc = null;
		String bodyContent = null;
		String MultipleMailID = "danielwilliam@gmail.com";
		String[] toEmailArray = null;

		toEmailArray = MultipleMailID.split("\\;");

		String subject = "Test Email";
		bodyContent = "Testing Purpose";
		Arrays.toString((Object[]) toEmailArray);
		String smtpHost = "smtp.gmail.com";
		String emailUser = "danielwilliam@encoretheme.com";
		String smtpPort = "586";
		String emailPassword = "Dany@220295";
		mastRefnc = "MasterReference";

		try {
			String Ma = sendEmail(smtpHost, emailUser, smtpPort, emailPassword,
					subject, bodyContent, toEmailArray, mastRefnc);

			System.out.println("Ma" + Ma);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String sendEmail(String host, String fromEmailID,
			String port, String password, String subject, String body,
			String[] to, String mastRefnc) {
		String mailReleaseStatus = "";
		String SMTPHost = host;
		String SMTPPort = port;
		final String EmailUser = fromEmailID;
		final String EmailPassword = password;
		String[] AlertEmail = to;
		try {
			System.out.println("mail.smtp.host" + SMTPHost);
			System.out.println("mail.smtp.port" + SMTPPort);
			System.out.println("EmailPassword" + EmailPassword);
			System.out.println("EmailUser" + EmailUser);
			Properties props = System.getProperties();
			props.put("mail.smtp.host", SMTPHost);
			props.put("mail.smtp.port", SMTPPort);
			props.put("mail.smtp.auth", "true");
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(EmailUser, EmailPassword);
				}
			});
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom((Address) new InternetAddress(EmailUser));
			System.out.println("AlertEmail :. " + AlertEmail);
			if (AlertEmail != null) {
				InternetAddress[] toAddress = new InternetAddress[AlertEmail.length];
				int i;
				for (i = 0; i < AlertEmail.length; i++) {
					System.out.println("to[i] :  " + AlertEmail[i]);
					if (BOCCommonUtil.isValidEmailAddress(AlertEmail[i])
							&& !AlertEmail[i].equalsIgnoreCase(""))
						toAddress[i] = new InternetAddress(AlertEmail[i]);
				}
				for (i = 0; i < toAddress.length; i++) {
					if (BOCCommonUtil.isValidEmailAddress(toAddress[i]
							.getAddress()))
						mimeMessage.addRecipient(Message.RecipientType.TO,
								(Address) toAddress[i]);
				}
			}
			mimeMessage.setSubject(subject);
			mimeMessage.setSentDate(new Date());
			MimeMultipart mimeMultipart = new MimeMultipart();
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(body, "text/plain");
			mimeMultipart.addBodyPart((BodyPart) mimeBodyPart);

			mimeMessage.setContent((Multipart) mimeMultipart);
			Transport.send((Message) mimeMessage);
			mailReleaseStatus = "SUCCEEDED";
			System.out.println("Alert Email Sent successfully!");
		} catch (Exception e) {

			mailReleaseStatus = "FAILED|Mail sending failed due to : "
					+ e.getMessage();
			e.printStackTrace();
		}
		return mailReleaseStatus;
	}
}
