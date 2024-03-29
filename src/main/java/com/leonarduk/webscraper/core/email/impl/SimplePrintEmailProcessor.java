/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.webscraper.core.email.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.leonarduk.webscraper.core.email.EmailMessage;
import com.leonarduk.webscraper.core.email.EmailProcessor;

/**
 * The Class SimplePrintEmailProcessor.
 *
 * @author Stephen Leonard
 * @version $Author: leonarduk $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date$: Date of last commit
 * @since 2 Feb 2015
 */
public class SimplePrintEmailProcessor implements EmailProcessor {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(SimplePrintEmailProcessor.class);
	Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	/*
	 * (non-Javadoc)
	 *
	 * @see com.leonarduk.core.email.EmailProcessor#process(com.leonarduk.core.email.EmailMessage)
	 */
	@Override
	public final boolean process(final EmailMessage emailMessage) {
		SimplePrintEmailProcessor.LOGGER.info(emailMessage.getSender() + " : "
		        + emailMessage.getSubject() + " on " + emailMessage.getSentDate());

		// Returns false so that it doesn't consume the message
		return false;
	}

}
