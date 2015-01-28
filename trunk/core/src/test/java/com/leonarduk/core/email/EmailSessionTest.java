/**
 *
 */
package com.leonarduk.core.email;

import javax.mail.Authenticator;
import javax.mail.Session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailSessionTest.
 */
public class EmailSessionTest {

	/** The user name. */
	private String	     userName;

	/** The password. */
	private String	     password;

	/** The server. */
	private String	     server;

	/** The port. */
	private String	     port;

	/** The session. */
	private EmailSession	session;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		this.userName = "testuser";
		this.password = "testpassword";
		this.server = "testserver";
		this.port = "1234";

		this.session = new EmailSession(this.userName, this.password, this.server, this.port);
	}

	/**
	 * Test get authenticator.
	 */
	@Test
	public void testGetAuthenticator() {
		final Authenticator authenticator = this.session.getAuthenticator(this.userName,
		        this.password);
		Assert.assertNotNull(authenticator);
	}

	/**
	 * Test get session.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetSession() throws Exception {
		final Session session = this.session.getSession();
		Assert.assertNotNull(session);
		Assert.assertEquals(this.server, session.getProperty(EmailSession.MAIL_SMTP_HOST));
		Assert.assertEquals(this.port,
		        session.getProperty(EmailSession.MAIL_SMTP_SOCKET_FACTORY_PORT));
		Assert.assertEquals(this.userName, session.getProperty(EmailSession.MAIL_STMP_USER));
		Assert.assertEquals(this.password, session.getProperty(EmailSession.MAIL_SMTP_PASSWORD));

	}
}
