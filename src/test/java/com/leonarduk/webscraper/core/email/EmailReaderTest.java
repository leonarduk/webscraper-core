/**
 *
 */
package com.leonarduk.webscraper.core.email;

import java.io.File;
import java.io.IOException;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.leonarduk.webscraper.core.FileUtils;
import com.leonarduk.webscraper.core.email.impl.EmailReaderImpl;
import com.leonarduk.webscraper.core.email.impl.SimplePrintEmailProcessor;

/**
 * The Class EmailReaderTest.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev$: Revision of last commit
 * @version $Date: 2015-02-08 21:26:51 +0000 (Sun, 08 Feb 2015) $: Date of last commits
 * @since 8 Feb 2015
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Session.class })
public class EmailReaderTest {

	/** The reader. */
	private EmailReader reader;

	/** The server. */
	private String server;

	/** The user name. */
	private String userName;

	/** The password. */
	private String password;

	/** The server type. */
	private ServerType serverType;

	/** The attachments directory. */
	private String attachmentsDirectory;

	/** The email processor. */
	private EmailProcessor emailProcessor;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public final void setUp() throws Exception {
		this.reader = new EmailReaderImpl();

		this.server = "localhost";
		final File createTempDir = FileUtils.createTempDir();
		this.attachmentsDirectory = createTempDir.getAbsolutePath();
		this.emailProcessor = new SimplePrintEmailProcessor();
		this.password = "";
		this.serverType = ServerType.pop3;
		this.userName = "steve";
		createTempDir.deleteOnExit();

	}

	/**
	 * Test email reader.
	 */
	@Test
	public final void testEmailReader() {
		Assert.assertNotNull(this.reader);
	}

	/**
	 * Test process mail.
	 *
	 * @throws MessagingException
	 *             the messaging exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public final void testProcessMail() throws MessagingException, IOException {

		Session.getDefaultInstance(System.getProperties(), null);
		PowerMockito.mockStatic(Session.class);
		final Session mockSession = Mockito.mock(Session.class);
		final Store store = Mockito.mock(Store.class);
		final Folder folder = Mockito.mock(Folder.class);
		Mockito.when(store.getDefaultFolder()).thenReturn(folder);
		Mockito.when(folder.getFolder("inbox")).thenReturn(folder);

		final Message testMessage = Mockito.mock(Message.class);
		final InternetAddress address = Mockito.mock(InternetAddress.class);
		Mockito.when(address.getPersonal()).thenReturn("test@email.com");
		final Address[] addresses = { address };
		Mockito.when(testMessage.getContent()).thenReturn("body text");
		Mockito.when(testMessage.getFrom()).thenReturn(addresses);
		final Message[] messages = new Message[] { testMessage };
		Mockito.when(folder.getMessages()).thenReturn(messages);

		Mockito.when(mockSession.getStore(Matchers.anyString())).thenReturn(store);

		Mockito.when(Session.getDefaultInstance(System.getProperties(), null))
		        .thenReturn(mockSession);

		this.reader.processMail(this.server, this.userName, this.password, this.serverType,
		        this.attachmentsDirectory, this.emailProcessor);
	}

}
