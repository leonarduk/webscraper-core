/**
 *
 */
package com.leonarduk.webscraper.core.email.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.FolderNotFoundException;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.ReadOnlyFolderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.StoreClosedException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;

import org.apache.log4j.Logger;

import com.leonarduk.webscraper.core.email.EmailMessage;
import com.leonarduk.webscraper.core.email.EmailProcessor;
import com.leonarduk.webscraper.core.email.EmailReader;
import com.leonarduk.webscraper.core.email.ServerType;

/**
 * The Class EmailReader.
 */
public class EmailReaderImpl implements EmailReader {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(EmailReader.class);

	/**
	 * Process mail.
	 *
	 * @param server
	 *            the server
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @param serverType
	 *            the server type
	 * @param attachmentsDirectory
	 *            the attachments directory
	 * @param emailProcessor
	 *            the email processor
	 */
	@Override
	public final void processMail(final String server, final String userName, final String password,
	        final ServerType serverType, final String attachmentsDirectory,
	        final EmailProcessor emailProcessor) {
		Session session = null;
		Store store = null;
		Folder folder = null;
		Message[] messages = null;
		Object messagecontentObject = null;
		String sender = null;
		String subject = null;
		Multipart multipart = null;
		Part part = null;
		String contentType = null;

		try {
			EmailReaderImpl.LOGGER.info("--------------processing mails started-----------------");
			session = Session.getDefaultInstance(System.getProperties(), null);

			EmailReaderImpl.LOGGER.info("getting the session for accessing email.");
			store = session.getStore(serverType.name());

			store.connect(server, userName, password);
			EmailReaderImpl.LOGGER.info("Connection established with IMAP server.");

			// Get a handle on the default folder
			folder = store.getDefaultFolder();

			EmailReaderImpl.LOGGER.info("Getting the Inbox folder.");

			// Retrieve the "Inbox"
			folder = folder.getFolder("inbox");

			// Reading the Email Index in Read / Write Mode
			folder.open(Folder.READ_WRITE);
			EmailReaderImpl.LOGGER.info("Retrieving messages.");

			// Retrieve the messages
			messages = folder.getMessages();

			final List<EmailMessage> emailList = new ArrayList<>();
			// Loop over all of the messages
			for (final Message message : messages) {
				final EmailMessageBuilderImpl emailMessageBuilder = new EmailMessageBuilderImpl();
				emailMessageBuilder.setSentDate(message.getSentDate());

				// Retrieve the message content
				messagecontentObject = message.getContent();

				EmailReaderImpl.LOGGER.debug(message.getAllHeaders());

				// Determine email type
				if (messagecontentObject instanceof Multipart) {
					// this.printData("Found Email with Attachment");
					sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
					// If the "personal" information has no entry, check the
					// address for the sender information

					if (sender == null) {
						sender = ((InternetAddress) message.getFrom()[0]).getAddress();
						// this.printData("sender in NULL. Printing Address:" +
						// sender);
					}
					emailMessageBuilder.setSender(sender);

					// Get the subject information
					subject = message.getSubject();
					emailMessageBuilder.setSubject(subject);

					// Retrieve the Multipart object from the message
					multipart = (Multipart) message.getContent();

					// this.printData("Retrieve the Multipart object from the message");

					// Loop over the parts of the email
					for (int i = 0; i < multipart.getCount(); i++) {
						// Retrieve the next part
						part = multipart.getBodyPart(i);
						contentType = part.getContentType();

						// Display the content type
						if (contentType.startsWith("text/plain")) {
							emailMessageBuilder.addContent(part.getContent().toString());
						}
						else {
							// Retrieve the file name
							final String attachmentPath = attachmentsDirectory + File.separator
							        + part.getFileName();
							((MimeBodyPart) part).saveFile(attachmentPath);
							emailMessageBuilder.addFile(attachmentPath);
						}
					}
				}
				else {
					// this.printData("Found Mail Without Attachment");
					sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
					// If the "personal" information has no entry, check the
					// address for the sender information

					if (sender == null) {
						sender = ((InternetAddress) message.getFrom()[0]).getAddress();
					}
					emailMessageBuilder.setSender(sender);

					// Get the subject information
					subject = message.getSubject();
					emailMessageBuilder.setSubject(subject);
					emailMessageBuilder.addContent(messagecontentObject.toString());
				}
				final EmailMessage emailMessage = emailMessageBuilder.create();
				emailProcessor.process(emailMessage);

				emailList.add(emailMessage);
			}

			// Close the folder
			folder.close(true);

			// Close the message store
			store.close();
		}
		catch (final AuthenticationFailedException | FolderClosedException | FolderNotFoundException
		        | NoSuchProviderException | ReadOnlyFolderException | StoreClosedException e) {
			EmailReaderImpl.LOGGER.error("Not able to process the mail reading.");
			e.printStackTrace();
		}
		catch (final Exception e) {
			EmailReaderImpl.LOGGER.error("Not able to process the mail reading.");
			e.printStackTrace();
		}
	}

}
