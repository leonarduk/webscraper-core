/**
 *
 */
package com.leonarduk.core.email;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * The Class EmailSender.
 */
public class EmailSender {

	/** The logger. */
	private static Logger logger = Logger.getLogger(EmailSender.class);

	/**
	 * Creates the message.
	 *
	 * @param fromEmail
	 *            the from email
	 * @param fromName
	 *            the from name
	 * @param subject
	 *            the subject
	 * @param msgBody
	 *            the msg body
	 * @param html
	 *            the html
	 * @param session
	 *            the session
	 * @param to
	 *            the to
	 * @return the message
	 * @throws MessagingException
	 *             the messaging exception
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	public final Message createMessage(final String fromEmail, final String fromName, final String subject,
			final String msgBody, final boolean html, final EmailSession session, final String... to)
					throws MessagingException, UnsupportedEncodingException {
		final Message msg = new MimeMessage(session.getSession());
		final InternetAddress[] addressTo = new InternetAddress[to.length];
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

	/**
	 * Send message.
	 *
	 * @param fromEmail
	 *            the from email
	 * @param fromName
	 *            the from name
	 * @param subject
	 *            the subject
	 * @param msgBody
	 *            the msg body
	 * @param html
	 *            the html
	 * @param session
	 *            the session
	 * @param to
	 *            the to
	 * @throws EmailException
	 *             the email exception
	 */
	public final void sendMessage(final String fromEmail, final String fromName, final String subject,
			final String msgBody, final boolean html, final EmailSession session, final String... to)
					throws EmailException {
		try {
			EmailSender.logger.info("sendMessage: from " + fromEmail + " to " + Arrays.asList(to));
			EmailSender.logger.debug("Text\n" + msgBody);
			final Message msg = this.createMessage(fromEmail, fromName, subject, msgBody, html, session, to);

			Transport.send(msg);

		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new EmailException("Failed to send message", e);
		}
	}

}
