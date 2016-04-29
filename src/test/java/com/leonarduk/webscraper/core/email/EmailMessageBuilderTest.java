/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.webscraper.core.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.leonarduk.webscraper.core.email.EmailMessageBuilder;

/**
 * The Class EmailMessageBuilderTest.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date: $: Date of last commit
 * @since 18 Mar 2015
 */
public class EmailMessageBuilderTest {

    /** The builder. */
    private EmailMessageBuilder builder;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public final void setUp() throws Exception {
        builder = new EmailMessageBuilder();
    }

    /**
     * Test email message builder.
     */
    @Test
    public final void testEmailMessageBuilder() {
        assertNotNull(this.builder);
    }

    /**
     * Test add file.
     */
    @Test
    public final void testAddFile() {
        String pathname = "/test/location";

        this.builder.addFile(pathname);
        List<String> files = new ArrayList<>();
        files.add(pathname);
        assertEquals(files, this.builder.create().getFiles());
    }

    /**
     * Test add content.
     */
    @Test
    public final void testAddContent() {
        String testData = "test content";
        this.builder.addContent(testData);
        String testData2 = "test content";
        this.builder.addContent(testData2);

        assertEquals(testData + testData2, this.builder.create()
                .getContentBuffer());
    }

    /**
     * Test set sender.
     */
    @Test
    public final void testSetSender() {
        String testData = "test@Sender.com";
        this.builder.setSender(testData);
        assertEquals(testData, this.builder.create().getSender());

    }

    /**
     * Test set sent date.
     */
    @Test
    public final void testSetSentDate() {
        Date testData = new Date();
        this.builder.setSentDate(testData);
        assertEquals(testData, this.builder.create().getSentDate());

    }

    /**
     * Test set subject.
     */
    @Test
    public final void testSetSubject() {
        String testData = "subject";
        this.builder.setSubject(testData);
        assertEquals(testData, this.builder.create().getSubject());

    }

}
