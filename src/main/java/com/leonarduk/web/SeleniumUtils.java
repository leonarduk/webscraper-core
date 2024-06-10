/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The Class SeleniumUtils.
 *
 * @author Stephen Leonard
 * @version $Author: leonarduk $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date$: Date of last commit
 * @since 3 Feb 2015
 */
public final class SeleniumUtils {

//	/**
//	 * Gets the download capable browser.
//	 *
//	 * @param tempDir         the temp dir
//	 * @param runInBackground the run in background
//	 * @return the download capable browser
//	 * @throws IOException Signals that an I/O exception has occurred.
//	 */
//	public static WebDriver getDownloadCapableBrowser(final File tempDir, final boolean runInBackground)
//			throws IOException {
//		if (!tempDir.exists()) {
//			throw new FileNotFoundException("Directory " + tempDir + " does not exist");
//		}
//
//		return SeleniumUtils.getFirefox(tempDir, runInBackground);
//		// getChrome(tempDir, runInBackground);
//	}
//
//	/**
//	 * Gets the download capable browser.
//	 *
//	 * @param downloadDir     the download dir
//	 * @param runInBackground the run in background
//	 * @return the download capable browser
//	 * @throws IOException Signals that an I/O exception has occurred.
//	 */
//	public static WebDriver getDownloadCapableBrowser(final String downloadDir, final boolean runInBackground)
//			throws IOException {
//		final File tempDir = new File(downloadDir);
//		return SeleniumUtils.getDownloadCapableBrowser(tempDir, runInBackground);
//
//	}
//
//	public static WebDriver getFirefox(final File tempDir, final boolean runInBackground) throws IOException {
//		FirefoxOptions fp = new FirefoxOptions().addPreference("browser.download.folderList", 2)
//				.addPreference("browser.download.manager.showWhenStarting", false)
//				.addPreference("browser.download.dir", tempDir.getCanonicalPath())
//				.addPreference("browser.helperApps.alwaysAsk.force", false)
//				.addPreference("browser.helperApps.neverAsk.saveToDisk",
//						"application/x-csv,text/html,text/ofx,text/qif,application/ofx,qif File"
//								+ "application/x-msmoney,application/x-ofx,application/x-qif,application/qif,text/qif,text/csv,text/x-csv,"
//								+ "application/x-download,application/vnd.ms-excel," + "application/pdf,text/plain")
//				.addPreference("browser.helperApps.neverAsk.openFile",
//						"application/x-msmoney,application/x-csv,text/html,text/ofx,application/ofx,"
//								+ "application/octet-stream,application/x-ofx,"
//								+ "application/vnd.ms-excel,text/csv,text/x-csv,application/qif,text/qif,"
//								+ "application/x-download,application/vnd.ms-excel," + "application/pdf,text/plain");
//
//		if (runInBackground) {
//			final String xport = System.getProperty("Importal.xvfb.id", ":1");
//			// System.out.println("XVFB: " + xport);
//			return new FirefoxDriver(
//					new GeckoDriverService.Builder().usingDriverExecutable(new File("path/to/geckodriver.exe"))
//							.withEnvironment(ImmutableMap.of("DISPLAY", xport)).build());
//		}
//
//
//		return new FirefoxDriver(fp);
//	}

	public static boolean isInternetAvailable() {
		return SeleniumUtils.isInternetAvailable("http://google.co.uk");
	}

	public static boolean isInternetAvailable(final String exampleUrl) {
		try {
			final URI testUrl = new URI(exampleUrl);
			testUrl.toURL().openConnection().connect();
			return true;
		} catch (@SuppressWarnings("unused") URISyntaxException | IOException e) {
			return false;
		}
	}

	/**
	 * Instantiates a new selenium utils.
	 */
	private SeleniumUtils() {
		throw new UnsupportedOperationException();
	}

}
