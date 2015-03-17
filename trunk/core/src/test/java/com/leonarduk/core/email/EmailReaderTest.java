/**
 *
 */
package com.leonarduk.core.email;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import javax.mail.Session;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.leonarduk.core.FileUtils;
import com.leonarduk.core.email.EmailReader.ServerType;

/**
 * The Class EmailReaderTest.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev$: Revision of last commit
 * @version $Date: 2015-02-08 21:26:51 +0000 (Sun, 08 Feb 2015) $: Date of last
 *          commit
 * @since 8 Feb 2015
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({
    Session.class
})
public class EmailReaderTest {

    private EmailReader reader;
    private String server;
    private String userName;
    private String password;
    private ServerType serverType;
    private String attachmentsDirectory;
    private EmailProcessor emailProcessor;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        reader = new EmailReader();

        this.server = "localhost";
        File createTempDir = FileUtils.createTempDir();
        this.attachmentsDirectory = createTempDir.getAbsolutePath();
        this.emailProcessor = new SimplePrintEmailProcessor();
        this.password = "";
        this.serverType = ServerType.pop3;
        this.userName = "steve";
        createTempDir.deleteOnExit();

    }

    /**
     * Test email reader.
     */
    @Test
    public void testEmailReader() {
        assertNotNull(this.reader);
    }

    /**
     * Test process mail.
     */
    @Test
    @Ignore
    public void testProcessMail() {

        Session.getDefaultInstance(System.getProperties(), null);
        Session mockSession = Mockito.mock(Session.class);
        Mockito.when(
                mockSession.getDefaultInstance(System.getProperties(), null))
                .thenReturn(mockSession);

        reader.processMail(server, userName, password, serverType,
                attachmentsDirectory, emailProcessor);
    }

}
