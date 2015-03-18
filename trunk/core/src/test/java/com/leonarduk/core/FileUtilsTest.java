/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

    @Test(expected = FileNotFoundException.class)
    public final void testWriteFileFailInvalidDir() throws IOException {
        final String fileName = "/root/file.tmp";
        final String contents = "test file\nline2\nline3";
        FileUtils.writeStringToFile(fileName, contents);
    }

    @Test(expected = IOException.class)
    public final void testCreateTempDirFail() throws IOException {
        FileUtils.createTempDir("/root");

    }

    @Test(expected = InvocationTargetException.class)
    public final void testConstructor() throws Exception {
        Constructor<FileUtils> c = FileUtils.class.getDeclaredConstructor();
        c.setAccessible(true);
        c.newInstance();
    }
}
