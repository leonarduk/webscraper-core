/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.email;

import java.util.LinkedList;
import java.util.List;

/**
 * The Class EmailProcessorChain.
 *
 * @author Stephen Leonard
 * @version $Author:: $: Author of last commit
 * @version $Rev:: $: Revision of last commit
 * @version $Date:: $: Date of last commit
 * @since 3 Feb 2015
 */
public class EmailProcessorChain implements EmailProcessor {

	/** The processors. */
	private final List<EmailProcessor> processors;

	/**
	 * Instantiates a new email processor chain.
	 */
	public EmailProcessorChain() {
		this.processors = new LinkedList<>();
	}

	/**
	 * Adds the processor.
	 *
	 * @param processor
	 *            the processor
	 */
	public final void addProcessor(final EmailProcessor processor) {
		this.processors.add(processor);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.leonarduk.core.email.EmailProcessor#process(com.leonarduk.core.email.EmailMessage)
	 */
	@Override
	public final boolean process(final EmailMessage emailMessage) throws Exception {
		for (final EmailProcessor processor : this.processors) {
			if (processor.process(emailMessage)) {
				return true;
			}
		}
		return false;
	}
}
