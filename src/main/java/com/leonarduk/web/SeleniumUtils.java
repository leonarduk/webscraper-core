/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

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

	public static WebDriver getChrome(final File downloadFilepath) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\Chrome.exe");
		final HashMap<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("profile.default_content_settings.popups", Integer.valueOf(0));
		chromePrefs.put("download.default_directory", downloadFilepath);
		final ChromeOptions options = new ChromeOptions();
		final HashMap<String, Object> chromeOptionsMap = new HashMap<>();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");
		final DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		final WebDriver driver = new ChromeDriver(cap);
		return driver;
	}

	/**
	 * Gets the download capable browser.
	 *
	 * @param tempDir the temp dir
	 * @return the download capable browser
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static WebDriver getDownloadCapableBrowser(final File tempDir) throws IOException {
		return SeleniumUtils.getDownloadCapableBrowser(tempDir, false);
	}

	/**
	 * Gets the download capable browser.
	 *
	 * @param tempDir         the temp dir
	 * @param runInBackground the run in background
	 * @return the download capable browser
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static WebDriver getDownloadCapableBrowser(final File tempDir, final boolean runInBackground)
			throws IOException {
		if (!tempDir.exists()) {
			throw new FileNotFoundException("Directory " + tempDir + " does not exist");
		}

		return SeleniumUtils.getFirefox(tempDir, runInBackground);
		// getChrome(tempDir, runInBackground);
	}

	/**
	 * Gets the download capable browser.
	 *
	 * @param downloadDir the download dir
	 * @return the download capable browser
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static WebDriver getDownloadCapableBrowser(final String downloadDir) throws IOException {
		return SeleniumUtils.getDownloadCapableBrowser(downloadDir, false);
	}

	/**
	 * Gets the download capable browser.
	 *
	 * @param downloadDir     the download dir
	 * @param runInBackground the run in background
	 * @return the download capable browser
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static WebDriver getDownloadCapableBrowser(final String downloadDir, final boolean runInBackground)
			throws IOException {
		final File tempDir = new File(downloadDir);
		return SeleniumUtils.getDownloadCapableBrowser(tempDir, runInBackground);

	}

	public static WebDriver getFirefox(final File tempDir, final boolean runInBackground) throws IOException {
		FirefoxOptions fp = new FirefoxOptions().addPreference("browser.download.folderList", 2)
				.addPreference("browser.download.manager.showWhenStarting", false)
				.addPreference("browser.download.dir", tempDir.getCanonicalPath())
				.addPreference("browser.helperApps.alwaysAsk.force", false)
				.addPreference("browser.helperApps.neverAsk.saveToDisk",
						"application/x-csv,text/html,text/ofx,application/ofx,"
								+ "application/x-ofx,application/x-qif,text/csv,text/x-csv,"
								+ "application/x-download,application/vnd.ms-excel," + "application/pdf,text/plain")
				.addPreference("browser.helperApps.neverAsk.openFile",
						"application/x-csv,text/html,text/ofx,application/ofx,"
								+ "application/octet-stream,application/x-ofx,"
								+ "application/vnd.ms-excel,text/csv,text/x-csv,"
								+ "application/x-download,application/vnd.ms-excel," + "application/pdf,text/plain");

		if (runInBackground) {
			final String xport = System.getProperty("Importal.xvfb.id", ":1");
			// System.out.println("XVFB: " + xport);
			return new FirefoxDriver(
					new GeckoDriverService.Builder().usingDriverExecutable(new File("path/to/geckodriver.exe"))
							.withEnvironment(ImmutableMap.of("DISPLAY", xport)).build());
		}

		return new FirefoxDriver(fp);
	}

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
