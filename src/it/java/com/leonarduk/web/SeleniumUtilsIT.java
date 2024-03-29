/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
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

public class SeleniumUtilsIT {

	/**
	 * Test get download capable browser file.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
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
	public final void testGetDownloadCapableBrowserString() throws IOException {
		final File createTempDir = FileUtils.createTempDir();
		final WebDriver browser = SeleniumUtils
		        .getDownloadCapableBrowser(createTempDir.getAbsolutePath(), true);
		createTempDir.deleteOnExit();

		browser.close();

	}

	@Test
	public final void testIsInternetAvailable() throws Exception {
		Assert.assertTrue(SeleniumUtils.isInternetAvailable());
	}
}
