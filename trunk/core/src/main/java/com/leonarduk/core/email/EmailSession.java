/**
 *
 */
package com.leonarduk.core.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * The Class EmailSession.
 */
public class EmailSession {

	/** The Constant MAIL_SMTP_PASSWORD. */
	public static final String	MAIL_SMTP_PASSWORD	          = "mail.smtp.password";

	/** The Constant MAIL_SMTP_SOCKET_FACTORY_PORT. */
	public static final String	MAIL_SMTP_SOCKET_FACTORY_PORT	= "mail.smtp.socketFactory.port";

	/** The Constant MAIL_SMTP_HOST. */
	public static final String	MAIL_SMTP_HOST	              = "mail.smtp.host";

	/** The Constant MAIL_STMP_USER. */
	public static final String	MAIL_STMP_USER	              = "mail.stmp.user";

	/** The session. */
	private final Session	   session;

	/**
	 * Instantiates a new email session.
	 *
	 * @param user
	 *            the user
	 * @param password
	 *            the password
	 * @param server
	 *            the server
	 * @param port
	 *            the port
	 */
	public EmailSession(final String user, final String password, final String server,
			final String port) {
		this.session = this.createSession(user, password, server, port);
		// InternetAddress from = new InternetAddress(address, personal);
	}

	/**
	 * Creates the session.
	 *
	 * @param user
	 *            the user
	 * @param password
	 *            the password
	 * @param server
	 *            the server
	 * @param port
	 *            the port
	 * @return the session
	 */
	public Session createSession(final String user, final String password, final String server,
			final String port) {

		final Properties props = new Properties();
		props.put(EmailSession.MAIL_SMTP_HOST, server);
		// If you want to use SSL
		props.put(EmailSession.MAIL_SMTP_SOCKET_FACTORY_PORT, port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put(EmailSession.MAIL_STMP_USER, user);
		// If you want you use TLS
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.starttls.enable", "true");
		props.put(EmailSession.MAIL_SMTP_PASSWORD, password);
		final Session session = Session.getDefaultInstance(props,
				this.getAuthenticator(user, password));
		return session;
	}

	/**
	 * Gets the authenticator.
	 *
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the authenticator
	 */
	public Authenticator getAuthenticator(final String userName, final String password) {
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
	public Session getSession() {
		return this.session;
	}

}
