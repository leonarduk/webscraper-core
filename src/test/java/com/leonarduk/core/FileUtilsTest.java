/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class FileUtilsTest {

    /**
     * Test create temp dir.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public final void testCreateTempDir() throws IOException {
        final File directory = FileUtils.createTempDir();
        Assert.assertTrue(directory.isDirectory());
        Assert.assertTrue(directory.canRead());
        directory.deleteOnExit();
    }

    @Test
    public final void testWriteAndReadFile() throws IOException {
        final String fileName =
                FileUtils.createTempDir().getAbsolutePath() + "/file.tmp";
        final String contents = "test file\nline2\nline3";

        FileUtils.writeStringToFile(fileName, contents);
        Assert.assertEquals(contents, FileUtils.getFileContents(fileName));
    }

    @Test(expected = IOException.class)
    public final void testCreateTempDirFail() throws IOException {
        FileUtils.createTempDir("/root");

    }
}
