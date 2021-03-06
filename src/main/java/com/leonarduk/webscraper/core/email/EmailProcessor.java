/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.webscraper.core.email;

/**
 * The Interface EmailProcessor.
 *
 * @author Stephen Leonard
 * @version $Author: leonarduk $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date$: Date of last commit
 * @since 2 Feb 2015
 */
public interface EmailProcessor {

	/**
	 * Process.
	 *
	 * @param emailMessage
	 *            the email message
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	boolean process(EmailMessage emailMessage) throws Exception;

}
