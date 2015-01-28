/**
 *
 */
package com.leonarduk.core.email;

import javax.mail.Message;
import javax.mail.Transport;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Transport.class })
public class EmailSenderTest {

	private EmailSender	sender;
	private String	    fromEmail;
	private String	    fromName;
	private String	    subject;
	private String	    msgBody;
	private String[]	to;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		this.sender = new EmailSender();
		this.to = new String[] { "test1@localhost", "test2@localhost" };
		this.fromEmail = "testsender@test";
		this.fromName = "Test Sender";
		this.subject = "Test subject";

	}

	@Test
	public void testCreateMessage() throws Exception {
		final EmailSession session = Mockito.mock(EmailSession.class);
		final boolean html = true;
		final Message message = this.sender.createMessage(this.fromEmail, this.fromName,
		        this.subject, this.msgBody, html, session, this.to);
		Assert.assertNotNull(message);
	}

	@Test
	public void testSendMessage() throws Exception {
		final EmailSession session = Mockito.mock(EmailSession.class);
		MemberModifier.suppress(MemberMatcher.method(Transport.class, "send", Message.class));

		this.sender.sendMessage(this.fromEmail, this.fromName, this.subject, this.msgBody, true,
		        session, this.to);
	}
}
