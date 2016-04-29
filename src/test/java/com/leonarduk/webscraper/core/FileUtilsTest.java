/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.webscraper.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;

import com.leonarduk.webscraper.core.FileUtils;

/**
 * The Class FileUtilsTest.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date: $: Date of last commit
 * @since 18 Mar 2015
 */
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

    /**
     * Test write and read file.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public final void testWriteAndReadFile() throws IOException {
        final String fileName =
                FileUtils.createTempDir().getAbsolutePath() + "/file.tmp";
        final String contents = "test file\nline2\nline3";

        FileUtils.writeStringToFile(fileName, contents);
        Assert.assertEquals(contents, FileUtils.getFileContents(fileName));
    }

    /**
     * Test write file fail invalid dir.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test(expected = FileNotFoundException.class)
    public final void testWriteFileFailInvalidDir() throws IOException {
        final String fileName = "/root/file.tmp";
        final String contents = "test file\nline2\nline3";
        FileUtils.writeStringToFile(fileName, contents);
    }

    /**
     * Test create temp dir fail.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test(expected = IOException.class)
    public final void testCreateTempDirFail() throws IOException {
        FileUtils.createTempDir("/root");

    }

    /**
     * Test constructor.
     *
     * @throws Exception
     *             the exception
     */
    @Test(expected = InvocationTargetException.class)
    public final void testConstructor() throws Exception {
        Constructor<FileUtils> c = FileUtils.class.getDeclaredConstructor();
        c.setAccessible(true);
        c.newInstance();
    }
}
