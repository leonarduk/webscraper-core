/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.webscraper.core.email;

import java.util.Date;
import java.util.List;

/**
 * The Class EmailMessage.
 *
 * @author Stephen Leonard
 * @version $Author: leonarduk $ Author of last commit
 * @version $Rev: 364 $: Revision of last commit
 * @version $Date$: Date of last commit
 * @since 2 Feb 2015
 */
public class EmailMessage {

	/** The content buffer. */
	private final String contentBuffer;

	/** The files. */
	private final List<String> filesList;

	/** The sender. */
	private final String senderString;

	/** The sent date. */
	private final Date sentdate;

	/** The subject. */
	private final String subjectString;

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
		this.senderString = sender;
		this.subjectString = subject;
		this.contentBuffer = content;
		this.filesList = files;
		this.sentdate = sentDate;
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
		return this.filesList;
	}

	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	public final String getSender() {
		return this.senderString;
	}

	/**
	 * Gets the sent date.
	 *
	 * @return the sent date
	 */
	public final Date getSentDate() {
		return this.sentdate;
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public final String getSubject() {
		return this.subjectString;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "EmailMessage [contentBuffer=" + this.contentBuffer + ", sender=" + this.senderString
		        + ", files=" + this.filesList + ", subject=" + this.subjectString + "]";
	}
}
