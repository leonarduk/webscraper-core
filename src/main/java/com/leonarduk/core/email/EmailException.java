/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.email;

/**
 * The Class EmailException.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date: $: Date of last commit
 * @since 19 Mar 2015
 */
public class EmailException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2685902008223929467L;

    /**
     * Instantiates a new email exception.
     *
     * @param string
     *            the string
     * @param e
     *            the e
     */
    public EmailException(final String string, final Exception e) {
        super(string, e);
    }

}
