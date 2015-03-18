/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.email;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * The Class SimplePrintEmailProcessorTest.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date: $: Date of last commit
 * @since 18 Mar 2015
 */
public class SimplePrintEmailProcessorTest {

    /**
     * Test process.
     */
    @Test
    public final void testProcess() {
        EmailMessage emailMessage =
                new EmailMessage(null, null, null, null, null);
        assertFalse(new SimplePrintEmailProcessor().process(emailMessage));
    }

}
