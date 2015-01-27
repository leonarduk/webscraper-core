package com.leonarduk.core.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;

public class EmailSession {
	public static final String MAIL_SMTP_PASSWORD = "mail.smtp.password";
	public static final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
	public static final String MAIL_SMTP_HOST = "mail.smtp.host";
	public static final String MAIL_STMP_USER = "mail.stmp.user";
	private Session session;

	public EmailSession(String user, String password, String server, String port) {
		this.session = createSession(user, password, server, port);
//		InternetAddress from = new InternetAddress(address, personal);
	}

	public Authenticator getAuthenticator(final String userName,
			final String password) {
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		return auth;
	}

	public Session getSession() {
		return this.session;
	}

	/**
	 * 
	 * @param user
	 * @param password
	 * @param server
	 * @param port
	 * @return
	 */
	public Session createSession(String user, String password, String server,
			String port) {

		Properties props = new Properties();
		props.put(MAIL_SMTP_HOST, server);
		// If you want to use SSL
		props.put(MAIL_SMTP_SOCKET_FACTORY_PORT, port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put(MAIL_STMP_USER, user);
		// If you want you use TLS
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.starttls.enable", "true");
		props.put(MAIL_SMTP_PASSWORD, password);
		Session session = Session.getDefaultInstance(props,
				getAuthenticator(user, password));
		return session;
	}

}
