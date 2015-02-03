/**
 *
 */
package com.leonarduk.core.email;

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

/**
 * The Class EmailReader.
 */
public class EmailReader {
	/**
	 * The Enum ServerType.
	 */
	public enum ServerType {

		/** The imap. */
		imap,
		/** The pop3. */
		pop3;
	}

	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(EmailReader.class);

	// Main Function for The readEmail Class
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		// Creating new readEmail Object
		final EmailReader readMail = new EmailReader();

		// Calling processMail Function to read from IMAP Account
		// readMail.processMail("leonarduk.com", "leonard", "SW179TNKT26LJ",
		// ServerType.imap);

		final String attachmentsFolder = ".";
		final EmailProcessor processor = new SimplePrintEmailProcessor();
		// Calling processMail Function to read from IMAP Account
		readMail.processMail("localhost", "steve", "", ServerType.pop3, attachmentsFolder,
				processor);
	}

	// Constructor Call
	/**
	 * Instantiates a new email reader.
	 */
	public EmailReader() {
	}

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
	public final void processMail(final String server, final String userName,
			final String password, final ServerType serverType, final String attachmentsDirectory,
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
			EmailReader.LOGGER.info("--------------processing mails started-----------------");
			session = Session.getDefaultInstance(System.getProperties(), null);

			EmailReader.LOGGER.info("getting the session for accessing email.");
			store = session.getStore(serverType.name());

			store.connect(server, userName, password);
			EmailReader.LOGGER.info("Connection established with IMAP server.");

			// Get a handle on the default folder
			folder = store.getDefaultFolder();

			EmailReader.LOGGER.info("Getting the Inbox folder.");

			// Retrieve the "Inbox"
			folder = folder.getFolder("inbox");

			// Reading the Email Index in Read / Write Mode
			folder.open(Folder.READ_WRITE);
			EmailReader.LOGGER.info("Retrieving messages.");

			// Retrieve the messages
			messages = folder.getMessages();

			final List<EmailMessage> emailList = new ArrayList<>();
			// Loop over all of the messages
			for (final Message message : messages) {
				final EmailMessageBuilder emailMessageBuilder = new EmailMessageBuilder();
				emailMessageBuilder.setSentDate(message.getSentDate());

				// Retrieve the message content
				messagecontentObject = message.getContent();

				EmailReader.LOGGER.debug(message.getAllHeaders());

				// Determine email type
				if (messagecontentObject instanceof Multipart) {
					// this.printData("Found Email with Attachment");
					sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
					// If the "personal" information has no entry, check the
					// address for the sender information

					if (sender == null) {
						sender = ((InternetAddress) message.getFrom()[0]).getAddress();
						// this.printData("sender in NULL. Printing Address:" + sender);
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
		catch (final AuthenticationFailedException | FolderClosedException
				| FolderNotFoundException | NoSuchProviderException | ReadOnlyFolderException
				| StoreClosedException e) {
			EmailReader.LOGGER.error("Not able to process the mail reading.");
			e.printStackTrace();
		}
		catch (final Exception e) {
			EmailReader.LOGGER.error("Not able to process the mail reading.");
			e.printStackTrace();
		}
	}

}
