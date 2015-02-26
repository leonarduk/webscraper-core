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
 * @version $Rev: 364 $: Revision of last commit
 * @version $Date$: Date of last commit
 * @since 2 Feb 2015
 */
public class EmailMessage {

	/** The content buffer. */
	private final String contentBuffer;

	/** The files. */
	private final List<String> files_;

	/** The sender. */
	private final String sender_;

	/** The sent date. */
	private final Date sentDate_;

	/** The subject. */
	private final String subject_;

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
		this.sender_ = sender;
		this.subject_ = subject;
		this.contentBuffer = content;
		this.files_ = files;
		this.sentDate_ = sentDate;
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
		return this.files_;
	}

	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	public final String getSender() {
		return this.sender_;
	}

	/**
	 * Gets the sent date.
	 *
	 * @return the sent date
	 */
	public final Date getSentDate() {
		return this.sentDate_;
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public final String getSubject() {
		return this.subject_;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "EmailMessage [contentBuffer=" + this.contentBuffer + ", sender=" + this.sender_
		        + ", files=" + this.files_ + ", subject=" + this.subject_ + "]";
	}
}
