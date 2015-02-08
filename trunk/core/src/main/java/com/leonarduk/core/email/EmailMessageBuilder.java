/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailMessageBuilder.
 *
 * @author Stephen Leonard
 * @version $Author: $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date$: Date of last commit
 * @since 2 Feb 2015
 */
public class EmailMessageBuilder {

	/** The content buffer. */
	private final StringBuilder contentBuffer;

	/** The sender. */
	private String sender;

	/** The files. */
	private final List<String> files;

	/** The subject. */
	private String subject;

	/** The sent date. */
	private Date sentDate;

	/**
	 * Instantiates a new email message builder.
	 */
	public EmailMessageBuilder() {
		this.contentBuffer = new StringBuilder();
		this.files = new ArrayList<String>();
	}

	/**
	 * Adds the content.
	 *
	 * @param content
	 *            the content
	 */
	public final void addContent(final String content) {
		this.contentBuffer.append(content);
	}

	/**
	 * Adds the file.
	 *
	 * @param fileName
	 *            the file name
	 */
	public final void addFile(final String fileName) {
		this.files.add(fileName);
	}

	/**
	 * Creates the.
	 *
	 * @return the email message
	 */
	public final EmailMessage create() {
		return new EmailMessage(this.sender, this.sentDate, this.subject,
		        this.contentBuffer.toString(), this.files);
	}

	/**
	 * Sets the sender.
	 *
	 * @param sender
	 *            the new sender
	 */
	public final void setSender(final String sender) {
		this.sender = sender;
	}

	/**
	 * Sets the sent date.
	 *
	 * @param sentDate
	 *            the new sent date
	 */
	public void setSentDate(final Date sentDate) {
		this.sentDate = sentDate;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject
	 *            the new subject
	 */
	public final void setSubject(final String subject) {
		this.subject = subject;
	}
}
