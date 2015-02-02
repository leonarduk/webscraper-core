/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.email;

/**
 * The Interface EmailProcessor.
 *
 * @author Stephen Leonard
 * @version $Author:: $: Author of last commit
 * @version $Rev:: $: Revision of last commit
 * @version $Date:: $: Date of last commit
 * @since 2 Feb 2015
 */
public interface EmailProcessor {

	void process(EmailMessage emailMessage);

}
