/**
 *
 */
package com.leonarduk.webscraper.core.email.impl;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.leonarduk.webscraper.core.email.EmailException;
import com.leonarduk.webscraper.core.email.EmailSender;
import com.leonarduk.webscraper.core.email.EmailSession;
import com.leonarduk.webscraper.core.format.Formatter;

/**
 * The Class EmailSender.
 */
public class EmailSenderImpl implements EmailSender {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(EmailSenderImpl.class);

	@Override
	public final Message createMessage(final String fromEmail, final String fromName,
	        final String subject, final String msgBody, final Formatter formatter,
	        final EmailSession session, final String... to)
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
		if (formatter.isHtml()) {
			final StringBuilder buf = new StringBuilder();

			buf.append(formatter.startFile());
			buf.append(msgBody);
			buf.append(formatter.endFile());

			msg.setContent(buf.toString(), "text/html");

		}
		else {
			msg.setText(msgBody);
		}
		return msg;
	}

	@Override
	public final void sendMessage(final String fromEmail, final String fromName,
	        final String subject, final String msgBody, final Formatter formatter,
	        final EmailSession session, final String... to) throws EmailException {

		try {
			EmailSenderImpl.logger
			        .info("sendMessage: from " + fromEmail + " to " + Arrays.asList(to));
			EmailSenderImpl.logger.debug("Text\n" + msgBody);
			final Message msg = this.createMessage(fromEmail, fromName, subject, msgBody, formatter,
			        session, to);

			Transport.send(msg);

		}
		catch (MessagingException | UnsupportedEncodingException e) {
			throw new EmailException("Failed to send message", e);
		}
	}

}
