/**
 *
 */
package com.leonarduk.core.email;

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

public class EmailReader {

	enum ServerType {
		imap, pop3;
	}

	// Main Function for The readEmail Class
	public static void main(final String args[]) {
		// Creating new readEmail Object
		final EmailReader readMail = new EmailReader();

		// Calling processMail Function to read from IMAP Account
		// readMail.processMail("leonarduk.com", "leonard", "SW179TNKT26LJ",
		// ServerType.imap);

		// Calling processMail Function to read from IMAP Account
		readMail.processMail("localhost", "steve", "", ServerType.pop3);
	}

	// Constructor Call
	public EmailReader() {
	}

	// Responsible for printing Data to Console
	private void printData(final String data) {
		System.out.println(data);
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
	 */
	public void processMail(final String server, final String userName, final String password,
	        final ServerType serverType) {
		Session session = null;
		Store store = null;
		Folder folder = null;
		Message message = null;
		Message[] messages = null;
		Object messagecontentObject = null;
		String sender = null;
		String subject = null;
		Multipart multipart = null;
		Part part = null;
		String contentType = null;

		try {
			this.printData("--------------processing mails started-----------------");
			session = Session.getDefaultInstance(System.getProperties(), null);

			this.printData("getting the session for accessing email.");
			store = session.getStore(serverType.name());

			store.connect(server, userName, password);
			this.printData("Connection established with IMAP server.");

			// Get a handle on the default folder
			folder = store.getDefaultFolder();

			this.printData("Getting the Inbox folder.");

			// Retrieve the "Inbox"
			folder = folder.getFolder("inbox");

			// Reading the Email Index in Read / Write Mode
			folder.open(Folder.READ_WRITE);

			// Retrieve the messages
			messages = folder.getMessages();

			// Loop over all of the messages
			for (final Message message2 : messages) {
				// Retrieve the next message to be read
				message = message2;

				// Retrieve the message content
				messagecontentObject = message.getContent();

				// Determine email type
				if (messagecontentObject instanceof Multipart) {
					this.printData("Found Email with Attachment");
					sender = ((InternetAddress) message.getFrom()[0]).getPersonal();

					// If the "personal" information has no entry, check the
					// address for the sender information
					this.printData("If the personal information has no entry, check the address for the sender information.");

					if (sender == null) {
						sender = ((InternetAddress) message.getFrom()[0]).getAddress();
						this.printData("sender in NULL. Printing Address:" + sender);
					}
					this.printData("Sender -." + sender);

					// Get the subject information
					subject = message.getSubject();

					this.printData("subject=" + subject);

					// Retrieve the Multipart object from the message
					multipart = (Multipart) message.getContent();

					this.printData("Retrieve the Multipart object from the message");

					// Loop over the parts of the email
					for (int i = 0; i < multipart.getCount(); i++) {
						// Retrieve the next part
						part = multipart.getBodyPart(i);

						// Get the content type
						contentType = part.getContentType();

						// Display the content type
						this.printData("Content: " + contentType);

						if (contentType.startsWith("text/plain")) {
							this.printData("---------reading content type text/plain  mail -------------");

							System.out.println(part.getContent());
						}
						else {
							// Retrieve the file name
							final String fileName = part.getFileName();
							this.printData("retrive the fileName=" + fileName);
						}
					}
				}
				else {
					this.printData("Found Mail Without Attachment");
					sender = ((InternetAddress) message.getFrom()[0]).getPersonal();

					// If the "personal" information has no entry, check the
					// address for the sender information
					this.printData("If the personal information has no entry, check the address for the sender information.");

					if (sender == null) {
						sender = ((InternetAddress) message.getFrom()[0]).getAddress();
						this.printData("sender in NULL. Printing Address:" + sender);
					}

					// Get the subject information
					subject = message.getSubject();
					this.printData("subject=" + subject);

					System.out.println(messagecontentObject.toString());
				}
			}

			// Close the folder
			folder.close(true);

			// Close the message store
			store.close();
		}
		catch (final AuthenticationFailedException e) {
			this.printData("Not able to process the mail reading.");
			e.printStackTrace();
		}
		catch (final FolderClosedException e) {
			this.printData("Not able to process the mail reading.");
			e.printStackTrace();
		}
		catch (final FolderNotFoundException e) {
			this.printData("Not able to process the mail reading.");
			e.printStackTrace();
		}
		catch (final NoSuchProviderException e) {
			this.printData("Not able to process the mail reading.");
			e.printStackTrace();
		}
		catch (final ReadOnlyFolderException e) {
			this.printData("Not able to process the mail reading.");
			e.printStackTrace();
		}
		catch (final StoreClosedException e) {
			this.printData("Not able to process the mail reading.");
			e.printStackTrace();
		}
		catch (final Exception e) {
			this.printData("Not able to process the mail reading.");
			e.printStackTrace();
		}
	}

}
