package com.leonarduk.core.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class EmailSender {
	private static Logger logger = Logger.getLogger(EmailSender.class);

	/**
	 * 
	 * @param fromEmail
	 * @param fromName
	 * @param subject
	 * @param msgBody
	 * @param html
	 * @param session
	 * @param to
	 */
	public void sendMessage(String fromEmail, String fromName, String subject,
			String msgBody, boolean html, EmailSession session, String... to) {
		try {
			logger.info("sendMessage: from " + fromEmail + " to " + to);
			Message msg = createMessage(fromEmail, fromName, subject, msgBody,
					html, session, to);

			Transport.send(msg);

		} catch (MessagingException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param fromEmail
	 * @param fromName
	 * @param subject
	 * @param msgBody
	 * @param html
	 * @param session
	 * @param to
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public Message createMessage(String fromEmail, String fromName,
			String subject, String msgBody, boolean html, EmailSession session,
			String... to) throws AddressException, MessagingException,
			UnsupportedEncodingException {
		Message msg = new MimeMessage(session.getSession());
		InternetAddress[] addressTo = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++) {
			addressTo[i] = new InternetAddress(to[i]);
		}
		msg.setRecipients(RecipientType.TO, addressTo);

		msg.setFrom(new InternetAddress(fromEmail, fromName));
		msg.setSentDate(new Date());
		msg.setSubject(subject);
		if (html) {
			msg.setContent(msgBody, "text/html");
		} else {
			msg.setText(msgBody);
		}
		return msg;
	}

}
