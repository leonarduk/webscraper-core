package com.leonarduk.core.email;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

import javax.mail.Message;
import javax.mail.Transport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Transport.class })
public class EmailSenderTest {

	private EmailSender sender;
	private String fromEmail;
	private String fromName;
	private String subject;
	private String msgBody;
	private String[] to;

	@Before
	public void setUp() {
		sender = new EmailSender();
		to = new String[] { "test1@localhost", "test2@localhost" };
		this.fromEmail = "testsender@test";
		this.fromName = "Test Sender";
		this.subject = "Test subject";

	}

	@Test
	public void testCreateMessage() throws Exception {
		EmailSession session = mock(EmailSession.class);
		boolean html = true;
		Message message = sender.createMessage(fromEmail, fromName, subject,
				msgBody, html, session, to);
		assertNotNull(message);
	}

	@Test
	public void testSendMessage() throws Exception {
		EmailSession session = mock(EmailSession.class);
		suppress(method(Transport.class, "send", Message.class));

		this.sender.sendMessage(fromEmail, fromName, subject, msgBody, true,
				session, to);
	}
}
