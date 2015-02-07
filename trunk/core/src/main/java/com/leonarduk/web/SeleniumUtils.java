/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class SeleniumUtils.
 *
 * @author Stephen Leonard
 * @version $Author:: $: Author of last commit
 * @version $Rev:: $: Revision of last commit
 * @version $Date:: $: Date of last commit
 * @since 3 Feb 2015
 */
public class SeleniumUtils {

	/**
	 * Creates the temp dir.
	 *
	 * @return the file
	 */
	public static File createTempDir() {
		final String tmpDirPath = System.getProperty("java.io.tmpdir")
				+ System.getProperty("file.separator") + RandomStringUtils.randomAlphanumeric(6);
		final File tempDir = new File(tmpDirPath);
		final boolean tmpDirCreated = tempDir.mkdir();
		return tempDir;
	}

	/**
	 * Gets the download capable browser.
	 *
	 * @param tempDir
	 *            the temp dir
	 * @return the download capable browser
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static WebDriver getDownloadCapableBrowser(final File tempDir) throws IOException {
		if (!tempDir.exists()) {
			throw new FileNotFoundException("Directory " + tempDir + " does not exist");
		}

		final FirefoxProfile fp = new FirefoxProfile();
		try {
			fp.setPreference("browser.download.folderList", 2);
			fp.setPreference("browser.download.manager.showWhenStarting", false);
			fp.setPreference("browser.download.dir", tempDir.getCanonicalPath());
			fp.setPreference("browser.helperApps.alwaysAsk.force", false);
			fp.setPreference(
					"browser.helperApps.neverAsk.saveToDisk",
					"application/x-csv,text/html,text/ofx,application/ofx,application/x-ofx,application/x-qif,text/csv,text/x-csv,application/x-download,application/vnd.ms-excel,application/pdf,text/plain");
			fp.setPreference(
					"browser.helperApps.neverAsk.openFile",
					"application/x-csv,text/html,text/ofx,application/ofx,application/octet-stream,application/x-ofx,application/vnd.ms-excel,text/csv,text/x-csv,application/x-download,application/vnd.ms-excel,application/pdf,text/plain");
		}
		catch (final IOException e) {
			throw new IOException("Failed to initialize Firefox with temp directory for downloads",
					e);
		}

		final WebDriver driver = new FirefoxDriver(fp);

		return driver;
	}

	/**
	 * Gets the download capable browser.
	 *
	 * @param downloadDir
	 *            the download dir
	 * @return the download capable browser
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static WebDriver getDownloadCapableBrowser(final String downloadDir) throws IOException {
		final File tempDir = new File(downloadDir);

		return SeleniumUtils.getDownloadCapableBrowser(tempDir);
	}

}
