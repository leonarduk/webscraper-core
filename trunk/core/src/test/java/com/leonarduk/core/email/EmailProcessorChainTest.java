package com.leonarduk.core.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class EmailProcessorChainTest {

    private EmailProcessorChain chain;

    @Before
    public void setUp() throws Exception {
        chain = new EmailProcessorChain();
    }

    @Test
    public final void testEmailProcessorChain() {
        assertNotNull(this.chain);
    }

    @Test
    public final void testAddProcessor() {
        assertEquals(0, this.chain.size());
        this.chain.addProcessor(getDummyEmailProcessor("test"));
        assertEquals(1, this.chain.size());
    }

    @Test
    public final void testProcess() throws Exception {
        String searchTerm = "search term";
        this.chain.addProcessor(getDummyEmailProcessor(searchTerm));
        EmailMessage emailMessage =
                new EmailMessage(null, null, searchTerm, null, null);
        assertTrue(this.chain.process(emailMessage));
    }

    @Test
    public final void testProcessNotRelevant() throws Exception {
        String searchTerm = "search term";
        this.chain.addProcessor(getDummyEmailProcessor(searchTerm));
        EmailMessage emailMessage =
                new EmailMessage(null, null, "something else", null, null);
        assertFalse(this.chain.process(emailMessage));
    }

    private
            EmailProcessor
            getDummyEmailProcessor(final String subjectSearchTerm) {

        return new EmailProcessor() {

            @Override
            public boolean process(EmailMessage emailMessage) throws Exception {
                return emailMessage.getSubject().contains(subjectSearchTerm);
            }

        };
    }
}
