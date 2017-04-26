/**
 * EmailSender
 *
 * @author ${author}
 * @since 04-Jun-2016
 */
package com.leonarduk.webscraper.core.email;

import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;

import com.leonarduk.webscraper.core.format.Formatter;

public interface EmailSender {

	Message createMessage(String fromEmail, String fromName, String subject, String msgBody,
	        Formatter formatter, EmailSession session, String... to)
	        throws MessagingException, UnsupportedEncodingException;

	void sendMessage(String fromEmail, String fromName, String subject, String msgBody,
	        Formatter formatter, EmailSession session, String... to) throws EmailException;
}
