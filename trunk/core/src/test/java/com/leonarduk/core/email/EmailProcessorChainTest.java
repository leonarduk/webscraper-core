/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class EmailProcessorChainTest.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date: $: Date of last commit
 * @since 18 Mar 2015
 */
public class EmailProcessorChainTest {

    /** The chain. */
    private EmailProcessorChain chain;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public final void setUp() throws Exception {
        chain = new EmailProcessorChain();
    }

    /**
     * Test email processor chain.
     */
    @Test
    public final void testEmailProcessorChain() {
        assertNotNull(this.chain);
    }

    /**
     * Test add processor.
     */
    @Test
    public final void testAddProcessor() {
        assertEquals(0, this.chain.size());
        this.chain.addProcessor(getDummyEmailProcessor("test"));
        assertEquals(1, this.chain.size());
    }

    /**
     * Test process.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public final void testProcess() throws Exception {
        String searchTerm = "search term";
        this.chain.addProcessor(getDummyEmailProcessor(searchTerm));
        EmailMessage emailMessage =
                new EmailMessage(null, null, searchTerm, null, null);
        assertTrue(this.chain.process(emailMessage));
    }

    /**
     * Test process not relevant.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public final void testProcessNotRelevant() throws Exception {
        String searchTerm = "search term";
        this.chain.addProcessor(getDummyEmailProcessor(searchTerm));
        EmailMessage emailMessage =
                new EmailMessage(null, null, "something else", null, null);
        assertFalse(this.chain.process(emailMessage));
    }

    /**
     * Gets the dummy email processor.
     *
     * @param subjectSearchTerm
     *            the subject search term
     * @return the dummy email processor
     */
    private
            EmailProcessor
            getDummyEmailProcessor(final String subjectSearchTerm) {

        return new EmailProcessor() {

            @Override
            public boolean process(final EmailMessage emailMessage)
                    throws Exception {
                return emailMessage.getSubject().contains(subjectSearchTerm);
            }

        };
    }
}
