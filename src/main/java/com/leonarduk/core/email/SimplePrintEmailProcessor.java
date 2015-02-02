/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.email;

/**
 * The Class SimplePrintEmailProcessor.
 *
 * @author Stephen Leonard
 * @version $Author:: $: Author of last commit
 * @version $Rev:: $: Revision of last commit
 * @version $Date:: $: Date of last commit
 * @since 2 Feb 2015
 */
public class SimplePrintEmailProcessor implements EmailProcessor {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.leonarduk.core.email.EmailProcessor#process(com.leonarduk.core.email.EmailMessage)
	 */
	@Override
	public void process(final EmailMessage emailMessage) {
		System.out.println(emailMessage);
	}

}
