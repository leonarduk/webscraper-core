///**
// *
// */
//package com.leonarduk.webscraper.core.email;
//
//import javax.mail.Message;
//import javax.mail.Transport;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.powermock.api.support.membermodification.MemberMatcher;
//import org.powermock.api.support.membermodification.MemberModifier;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import com.leonarduk.webscraper.core.email.impl.EmailSenderImpl;
//import com.leonarduk.webscraper.core.email.impl.EmailSessionImpl;
//import com.leonarduk.webscraper.core.format.Formatter;
//import com.leonarduk.webscraper.core.format.HtmlFormatter;
//
///**
// * The Class EmailSenderTest.
// *
// * @author stephen
// * @version $Author: $: Author of last commit
// * @version $Rev$: Revision of last commit
// * @version $Date: 2015-02-08 21:26:51 +0000 (Sun, 08 Feb 2015) $: Date of last commit
// * @since 8 Feb 2015
// */
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ Transport.class })
//public class EmailSenderTest {
//
//	/** The sender. */
//	private EmailSenderImpl sender;
//
//	/** The from email. */
//	private String fromEmail;
//
//	/** The from name. */
//	private String fromName;
//
//	/** The subject. */
//	private String subject;
//
//	/** The msg body. */
//	private String msgBody;
//
//	/** The to. */
//	private String[] to;
//
//	private Formatter formatter;
//
//	/**
//	 * Sets the up.
//	 */
//	@Before
//	public final void setUp() {
//		this.sender = new EmailSenderImpl();
//		this.to = new String[] { "test1@localhost", "test2@localhost" };
//		this.fromEmail = "testsender@test";
//		this.fromName = "Test Sender";
//		this.subject = "Test subject";
//		this.formatter = new HtmlFormatter();
//		this.msgBody = this.formatter.formatHeader("test message contents");
//	}
//
//	/**
//	 * Test create message.
//	 *
//	 * @throws Exception
//	 *             the exception
//	 */
//	@Test
//	public final void testCreateMessage() throws Exception {
//		final EmailSessionImpl session = Mockito.mock(EmailSessionImpl.class);
//
//		final Message message = this.sender.createMessage(this.fromEmail, this.fromName,
//		        this.subject, this.msgBody, this.formatter, session, this.to);
//		Assert.assertNotNull(message);
//		Assert.assertEquals("<html><body><h1>test message contents</h1></body></html>",
//		        message.getContent());
//	}
//
//	/**
//	 * Test send message.
//	 *
//	 * @throws Exception
//	 *             the exception
//	 */
//	@Test
//	public final void testSendMessage() throws Exception {
//		final EmailSessionImpl session = Mockito.mock(EmailSessionImpl.class);
//		MemberModifier.suppress(MemberMatcher.method(Transport.class, "send", Message.class));
//
//		try {
//			this.sender.sendMessage(this.fromEmail, this.fromName, this.subject, this.msgBody,
//			        this.formatter, session, this.to);
//		}
//		catch (final Exception e) {
//			Assert.fail("Caught exception");
//		}
//	}
//
//}
