/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.web;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.leonarduk.webscraper.core.FileUtils;

/**
 * The Class SeleniumUtilsTest.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date: $: Date of last commit
 * @since 11 Mar 2015
 */

public class SeleniumUtilsTest {

	@Test
	@Ignore
	public final void testIsInternetAvailable() throws Exception {
		assertTrue(SeleniumUtils.isInternetAvailable());
	}
	
	/**
	 * Test constructor.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = InvocationTargetException.class)
	public final void testConstructor() throws Exception {
		Constructor<SeleniumUtils> c = SeleniumUtils.class.getDeclaredConstructor();
		c.setAccessible(true);
		c.newInstance();
	}

	/**
	 * Test get download capable browser file.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	@Ignore
	public final void testGetDownloadCapableBrowserFile() throws IOException {
		final File createTempDir = FileUtils.createTempDir();
		final WebDriver browser = SeleniumUtils.getDownloadCapableBrowser(createTempDir, true);
		createTempDir.deleteOnExit();
		browser.close();
	}

	/**
	 * Test get download capable browser invalid dir file.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(expected = FileNotFoundException.class)
	public final void testGetDownloadCapableBrowserInvalidDirFile() throws IOException {
		SeleniumUtils.getDownloadCapableBrowser(new File("invalidpath"), true);
	}

	/**
	 * Test get download capable browser invalid dir string.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(expected = FileNotFoundException.class)
	public final void testGetDownloadCapableBrowserInvalidDirString() throws IOException {
		SeleniumUtils.getDownloadCapableBrowser("invalidpath", true);
	}

	/**
	 * Test get download capable browser string.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	@Ignore
	public final void testGetDownloadCapableBrowserString() throws IOException {
		final File createTempDir = FileUtils.createTempDir();
		final WebDriver browser = SeleniumUtils.getDownloadCapableBrowser(createTempDir.getAbsolutePath(), true);
		createTempDir.deleteOnExit();
		browser.close();

	}
}
