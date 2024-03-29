/**
 * All rights reserved. @Leonard UK Ltd.
 *
 * @author stephen
 */
package com.leonarduk.webscraper.core.email;

import javax.mail.Authenticator;
import javax.mail.Session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.leonarduk.webscraper.core.email.impl.EmailSessionImpl;

/**
 * The Class EmailSessionTest.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev$: Revision of last commit
 * @version $Date: 2015-02-08 21:26:51 +0000 (Sun, 08 Feb 2015) $: Date of last
 *          commit
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
	private EmailSessionImpl session;

	private boolean useSsl;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public final void setUp() throws Exception {
		this.userName = "testuser";
		this.password = "testpassword";
		this.server = "testserver";
		this.port = "1234";
		this.useSsl = true;
		this.session = new EmailSessionImpl(this.userName, this.password, this.server, this.port, this.useSsl);
	}

	/**
	 * Test get authenticator.
	 */
	@Test
	public final void testGetAuthenticator() {
		final Authenticator authenticator = this.session.getAuthenticator(this.userName, this.password);
		Assert.assertNotNull(authenticator);
	}

	/**
	 * Test get session.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public final void testGetSession() throws Exception {
		final Session localSession = this.session.getSession();
		Assert.assertNotNull(localSession);
		Assert.assertEquals(this.server, localSession.getProperty(EmailSessionImpl.MAIL_SMTP_HOST));
		Assert.assertEquals(this.port, localSession.getProperty(EmailSessionImpl.MAIL_SMTP_SOCKET_FACTORY_PORT));
		Assert.assertEquals(this.userName, localSession.getProperty(EmailSessionImpl.MAIL_STMP_USER));
		Assert.assertEquals(this.password, localSession.getProperty(EmailSessionImpl.MAIL_SMTP_PASSWORD));

	}
}
