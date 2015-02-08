/**
 * All rights reserved. @Leonard UK Ltd.
 *
 * @author stephen
 */
package com.leonarduk.core.email;

import javax.mail.Authenticator;
import javax.mail.Session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class EmailSessionTest.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date$: Date of last commit
 * @since 8 Feb 2015
 */
public class EmailSessionTest {

	/** The user name. */
	private String userName;

	/** The password. */
	private String password;

	/** The server. */
	private String server;

	/** The port. */
	private String port;

	/** The session. */
	private EmailSession session;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public final void setUp() throws Exception {
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
	public final void testGetAuthenticator() {
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
	public final void testGetSession() throws Exception {
		final Session localSession = this.session.getSession();
		Assert.assertNotNull(localSession);
		Assert.assertEquals(this.server, localSession.getProperty(EmailSession.MAIL_SMTP_HOST));
		Assert.assertEquals(this.port,
		        localSession.getProperty(EmailSession.MAIL_SMTP_SOCKET_FACTORY_PORT));
		Assert.assertEquals(this.userName, localSession.getProperty(EmailSession.MAIL_STMP_USER));
		Assert.assertEquals(this.password,
		        localSession.getProperty(EmailSession.MAIL_SMTP_PASSWORD));

	}
}
