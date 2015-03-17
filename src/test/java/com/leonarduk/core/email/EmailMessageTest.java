/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class EmailMessageTest.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date: $: Date of last commit
 * @since 16 Mar 2015
 */
public class EmailMessageTest {

    private String sender;
    private Date sentDate;
    private String subject;
    private String content;
    private List<String> files;
    private EmailMessage message;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        sender = "test@sender.com";
        sentDate = new Date();
        subject = "test subject";
        content = "this is a test email\nsfsd";
        files = new ArrayList<>();
        files.add("/file/location1");
        files.add("/file/location2");

        message = new EmailMessage(sender, sentDate, subject, content, files);

    }

    /**
     * Test email message.
     */
    @Test
    public final void testEmailMessage() {
        assertNotNull(message);
    }

    /**
     * Test get content buffer.
     */
    @Test
    public final void testGetContentBuffer() {
        assertEquals(this.content, this.message.getContentBuffer());
    }

    /**
     * Test get files.
     */
    @Test
    public final void testGetFiles() {
        assertEquals(this.files, this.message.getFiles());
    }

    /**
     * Test get sender.
     */
    @Test
    public final void testGetSender() {
        assertEquals(this.sender, this.message.getSender());
    }

    /**
     * Test get sent date.
     */
    @Test
    public final void testGetSentDate() {
        assertEquals(this.sentDate, this.message.getSentDate());
    }

    /**
     * Test get subject.
     */
    @Test
    public final void testGetSubject() {
        assertEquals(this.subject, this.message.getSubject());
    }

    /**
     * Test to string.
     */
    @Test
    public final void testToString() {
        assertEquals(
                "EmailMessage [contentBuffer=this is a test email\n"
                        + "sfsd, sender=test@sender.com, files=[/file/location1, /file/location2], subject=test subject]",
                this.message.toString());
    }
}
