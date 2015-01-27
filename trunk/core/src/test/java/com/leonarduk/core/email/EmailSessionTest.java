package com.leonarduk.core.email;

import static org.junit.Assert.*;

import javax.mail.Authenticator;
import javax.mail.Session;

import org.junit.Before;
import org.junit.Test;

public class EmailSessionTest {

	private String userName;
	private String password;
	private String server;
	private String port;
	private EmailSession session;

	@Before
	public void setUp() throws Exception {
		userName = "testuser";
		password = "testpassword";
		server = "testserver";
		port = "1234";

		session = new EmailSession(userName, password, server, port);
	}

	@Test
	public void testGetAuthenticator() {
		Authenticator authenticator = session.getAuthenticator(userName,
				password);
		assertNotNull(authenticator);
	}

	@Test
	public void testGetSession() throws Exception {
		Session session = this.session.getSession();
		assertNotNull(session);
		assertEquals(server, session.getProperty(EmailSession.MAIL_SMTP_HOST));
		assertEquals(port,
				session.getProperty(EmailSession.MAIL_SMTP_SOCKET_FACTORY_PORT));
		assertEquals(userName, session.getProperty(EmailSession.MAIL_STMP_USER));
		assertEquals(password,
				session.getProperty(EmailSession.MAIL_SMTP_PASSWORD));

	}
}
