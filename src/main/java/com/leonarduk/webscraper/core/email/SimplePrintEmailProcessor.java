/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.webscraper.core.email;

import org.apache.log4j.Logger;

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
	static final Logger LOGGER = Logger.getLogger(SimplePrintEmailProcessor.class);

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
