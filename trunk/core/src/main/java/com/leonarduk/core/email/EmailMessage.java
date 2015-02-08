/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.email;

import java.util.Date;
import java.util.List;

/**
 * The Class EmailMessage.
 *
 * @author Stephen Leonard
 * @version $Author: leonarduk $ Author of last commit
 * @version $Rev$: Revision of last commit
 * @version $Date:: $: Date of last commit
 * @since 2 Feb 2015
 */
public class EmailMessage {

	/** The content buffer. */
	private final String contentBuffer;

	/** The files. */
	private final List<String> files;

	/** The sender. */
	private final String sender;

	/** The sent date. */
	private final Date sentDate;

	/** The subject. */
	private final String subject;

	/**
	 * Instantiates a new email message.
	 *
	 * @param sender
	 *            the sender
	 * @param sentDate
	 *            the sent date
	 * @param subject
	 *            the subject
	 * @param content
	 *            the content
	 * @param files
	 *            the files
	 */
	public EmailMessage(final String sender, final Date sentDate, final String subject,
			final String content, final List<String> files) {
		this.sender = sender;
		this.subject = subject;
		this.contentBuffer = content;
		this.files = files;
		this.sentDate = sentDate;
	}

	/**
	 * Gets the content buffer.
	 *
	 * @return the content buffer
	 */
	public final String getContentBuffer() {
		return this.contentBuffer;
	}

	/**
	 * Gets the files.
	 *
	 * @return the files
	 */
	public final List<String> getFiles() {
		return this.files;
	}

	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	public final String getSender() {
		return this.sender;
	}

	/**
	 * Gets the sent date.
	 *
	 * @return the sent date
	 */
	public final Date getSentDate() {
		return this.sentDate;
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public final String getSubject() {
		return this.subject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "EmailMessage [contentBuffer=" + this.contentBuffer + ", sender=" + this.sender
				+ ", files=" + this.files + ", subject=" + this.subject + "]";
	}
}
