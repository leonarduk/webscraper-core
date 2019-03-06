/**
 *
 */
package com.leonarduk.webscraper.core.email.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.leonarduk.webscraper.core.email.EmailSession;

/**
 * The Class EmailSession.
 */
public class EmailSessionImpl implements EmailSession {

	/** The Constant MAIL_SMTP_PASSWORD. */
	public static final String MAIL_SMTP_PASSWORD = "mail.smtp.password";

	/** The Constant MAIL_SMTP_SOCKET_FACTORY_PORT. */
	public static final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";

	/** The Constant MAIL_SMTP_HOST. */
	public static final String MAIL_SMTP_HOST = "mail.smtp.host";

	/** The Constant MAIL_STMP_USER. */
	public static final String MAIL_STMP_USER = "mail.stmp.user";

	/** The session. */
	private final Session session;

	/**
	 * Instantiates a new email session.
	 *
	 * @param user     the user
	 * @param password the password
	 * @param server   the server
	 * @param port     the port
	 * @param useSsl
	 */
	public EmailSessionImpl(final String user, final String password, final String server, final String port,
			boolean useSsl) {
		this.session = this.createSession(user, password, server, port, useSsl);
		// InternetAddress from = new InternetAddress(address, personal);
	}

	/**
	 * Creates the session.
	 *
	 * @param user     the user
	 * @param password the password
	 * @param server   the server
	 * @param port     the port
	 * @return the session
	 */
	@Override
	public final Session createSession(final String user, final String password, final String server, final String port,
			boolean useSsl) {

		final Properties props = new Properties();
		props.put(EmailSessionImpl.MAIL_SMTP_HOST, server);
		// If you want to use SSL
		props.put(EmailSessionImpl.MAIL_SMTP_SOCKET_FACTORY_PORT, port);
		props.put("mail.smtp.port", port);
		props.put(EmailSessionImpl.MAIL_STMP_USER, user);
		props.put(EmailSessionImpl.MAIL_SMTP_PASSWORD, password);
		
		if (useSsl) {
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			// If you want you use TLS
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.trust", "*");
			props.put("mail.smtp.socketFactory.fallback", "false");

			props.put("mail.smtp.starttls.enable", useSsl);
		}
		
		final Session localSession = Session.getDefaultInstance(props, this.getAuthenticator(user, password));
		return localSession;
	}

	/**
	 * Gets the authenticator.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @return the authenticator
	 */
	@Override
	public final Authenticator getAuthenticator(final String userName, final String password) {
		final Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		return auth;
	}

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	@Override
	public final Session getSession() {
		return this.session;
	}

}
