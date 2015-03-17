package com.leonarduk.core.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EmailMessageBuilderTest {

    private EmailMessageBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new EmailMessageBuilder();
    }

    @Test
    public final void testEmailMessageBuilder() {
        assertNotNull(this.builder);
    }

    @Test
    public final void testAddFile() {
        String pathname = "/test/location";

        this.builder.addFile(pathname);
        List<String> files = new ArrayList<>();
        files.add(pathname);
        assertEquals(files, this.builder.create().getFiles());
    }

    @Test
    public final void testAddContent() {
        String testData = "test content";
        this.builder.addContent(testData);
        String testData2 = "test content";
        this.builder.addContent(testData2);

        assertEquals(testData + testData2, this.builder.create()
                .getContentBuffer());
    }

    @Test
    public final void testSetSender() {
        String testData = "test@Sender.com";
        this.builder.setSender(testData);
        assertEquals(testData, this.builder.create().getSender());

    }

    @Test
    public final void testSetSentDate() {
        Date testData = new Date();
        this.builder.setSentDate(testData);
        assertEquals(testData, this.builder.create().getSentDate());

    }

    @Test
    public final void testSetSubject() {
        String testData = "subject";
        this.builder.setSubject(testData);
        assertEquals(testData, this.builder.create().getSubject());

    }

}
