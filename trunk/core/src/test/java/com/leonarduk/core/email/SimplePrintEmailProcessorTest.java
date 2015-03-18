package com.leonarduk.core.email;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class SimplePrintEmailProcessorTest {

    @Test
    public final void testProcess() {
        EmailMessage emailMessage =
                new EmailMessage(null, null, null, null, null);
        assertFalse(new SimplePrintEmailProcessor().process(emailMessage));
    }

}
