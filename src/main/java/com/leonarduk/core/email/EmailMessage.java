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
 * @version $Author:: $: Author of last commit
 * @version $Rev:: $: Revision of last commit
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

	/** The subject. */
	private final String subject;

	private final Date sentDate;

	/**
	 * Instantiates a new email message.
	 *
	 * @param sender
	 *            the sender
	 * @param sentDate
	 * @param subject
	 *            the subject
	 * @param string
	 *            the string
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

	@Override
	public final String toString() {
		return "EmailMessage [contentBuffer=" + this.contentBuffer + ", sender=" + this.sender
				+ ", files=" + this.files + ", subject=" + this.subject + "]";
	}
}
