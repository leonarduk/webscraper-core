/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * The Class SeleniumUtils.
 *
 * @author Stephen Leonard
 * @version $Author: leonarduk $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date$: Date of last
 *          commit
 * @since 3 Feb 2015
 */
public final class SeleniumUtils {

	public static boolean isInternetAvailable() {
		try {
			URI testUrl = new URI("http://google.co.uk");
			testUrl.toURL().openConnection().connect();
			return true;
		} catch (URISyntaxException e) {
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		
		return false;
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
    public static WebDriver getDownloadCapableBrowser(final File tempDir)
            throws IOException {
        return getDownloadCapableBrowser(tempDir, false);
    }

    /**
     * Gets the download capable browser.
     *
     * @param tempDir
     *            the temp dir
     * @param runInBackground
     *            the run in background
     * @return the download capable browser
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static WebDriver getDownloadCapableBrowser(
            final File tempDir,
            final boolean runInBackground) throws IOException {
        if (!tempDir.exists()) {
            throw new FileNotFoundException("Directory " + tempDir
                                            + " does not exist");
        }

        final FirefoxProfile fp = new FirefoxProfile();
        fp.setPreference("browser.download.folderList", 2);
        fp.setPreference("browser.download.manager.showWhenStarting", false);
        fp.setPreference("browser.download.dir", tempDir.getCanonicalPath());
        fp.setPreference("browser.helperApps.alwaysAsk.force", false);
        fp.setPreference(
                "browser.helperApps.neverAsk.saveToDisk",
                "application/x-csv,text/html,text/ofx,application/ofx,"
                        + "application/x-ofx,application/x-qif,text/csv,text/x-csv,"
                        + "application/x-download,application/vnd.ms-excel,"
                        + "application/pdf,text/plain");
        fp.setPreference("browser.helperApps.neverAsk.openFile",
                "application/x-csv,text/html,text/ofx,application/ofx,"
                        + "application/octet-stream,application/x-ofx,"
                        + "application/vnd.ms-excel,text/csv,text/x-csv,"
                        + "application/x-download,application/vnd.ms-excel,"
                        + "application/pdf,text/plain");

        if (runInBackground) {
            String xport = System.getProperty("Importal.xvfb.id", ":1");
            // System.out.println("XVFB: " + xport);
            FirefoxBinary firefoxBinary = new FirefoxBinary();
            firefoxBinary.setEnvironmentProperty("DISPLAY", xport);

            return new FirefoxDriver(firefoxBinary, fp);
        }

        return new FirefoxDriver(fp);
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
    public static WebDriver getDownloadCapableBrowser(final String downloadDir)
            throws IOException {
        return SeleniumUtils.getDownloadCapableBrowser(downloadDir, false);
    }

    /**
     * Gets the download capable browser.
     *
     * @param downloadDir
     *            the download dir
     * @param runInBackground
     *            the run in background
     * @return the download capable browser
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static WebDriver getDownloadCapableBrowser(
            final String downloadDir,
            final boolean runInBackground) throws IOException {
        final File tempDir = new File(downloadDir);
        return SeleniumUtils
                .getDownloadCapableBrowser(tempDir, runInBackground);

    }

    /**
     * Instantiates a new selenium utils.
     */
    private SeleniumUtils() {
        throw new UnsupportedOperationException();
    }

}
