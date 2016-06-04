/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.webscraper.core.email.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.leonarduk.webscraper.core.email.EmailMessage;
import com.leonarduk.webscraper.core.email.EmailMessageBuilder;

/**
 * The Class EmailMessageBuilder.
 *
 * @author Stephen Leonard
 * @version $Author: leonarduk $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date$: Date of last commit
 * @since 2 Feb 2015
 */
public class EmailMessageBuilderImpl implements EmailMessageBuilder {

	/** The content buffer. */
	private final StringBuilder contentBuffer;

	/** The sender. */
	private String senderString;

	/** The files. */
	private final List<String> files;

	/** The subject. */
	private String subjectString;

	/** The sent date. */
	private Date sentdate;

	/**
	 * Instantiates a new email message builder.
	 */
	public EmailMessageBuilderImpl() {
		this.contentBuffer = new StringBuilder();
		this.files = new ArrayList<>();
	}

	/**
	 * Adds the content.
	 *
	 * @param content
	 *            the content
	 */
	@Override
	public final void addContent(final String content) {
		this.contentBuffer.append(content);
	}

	/**
	 * Adds the file.
	 *
	 * @param fileName
	 *            the file name
	 */
	@Override
	public final void addFile(final String fileName) {
		this.files.add(fileName);
	}

	/**
	 * Creates the.
	 *
	 * @return the email message
	 */
	@Override
	public final EmailMessage create() {
		return new EmailMessage(this.senderString, this.sentdate, this.subjectString,
		        this.contentBuffer.toString(), this.files);
	}

	/**
	 * Sets the sender.
	 *
	 * @param sender
	 *            the new sender
	 */
	@Override
	public final void setSender(final String sender) {
		this.senderString = sender;
	}

	/**
	 * Sets the sent date.
	 *
	 * @param sentDate
	 *            the new sent date
	 */
	@Override
	public final void setSentDate(final Date sentDate) {
		this.sentdate = sentDate;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject
	 *            the new subject
	 */
	@Override
	public final void setSubject(final String subject) {
		this.subjectString = subject;
	}
}
