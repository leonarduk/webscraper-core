/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.email;

/**
 * The Interface EmailProcessor.
 *
 * @author Stephen Leonard
 * @version $Author$: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date: 2015-02-08 21:17:01 +0000 (Sun, 08 Feb 2015) $: Date of last commit
 * @since 2 Feb 2015
 */
public interface EmailProcessor {

	/**
	 * Process.
	 *
	 * @param emailMessage
	 *            the email message
	 * @return
	 * @throws BookkeeperException
	 */
	boolean process(EmailMessage emailMessage) throws Exception;

}
