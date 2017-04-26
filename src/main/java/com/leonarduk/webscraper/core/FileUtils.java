/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.webscraper.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * The Class FileUtils.
 *
 * @author stephen
 */

public final class FileUtils {

	/** The carriage return. */
	public static final String	CARRIAGE_RETURN	= "\n";

	/** The file separator. */
	public static final String	FILE_SEPARATOR	= System.getProperty("file.separator");

	/**
	 * Creates the temp dir.
	 *
	 * @return the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static File createTempDir() throws IOException {
		return FileUtils.createTempDir(System.getProperty("java.io.tmpdir"));
	}

	/**
	 * Creates the temp dir.
	 *
	 * @param baseDir
	 *            the base dir
	 * @return the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static File createTempDir(final String baseDir) throws IOException {
		final int lengthOfNumber = 6;
		final String tmpDirPath = baseDir + FileUtils.FILE_SEPARATOR
		        + RandomStringUtils.randomAlphanumeric(lengthOfNumber);
		final File tempDir = new File(tmpDirPath);
		final boolean tmpDirCreated = tempDir.mkdir();
		if (!tmpDirCreated) {
			throw new IOException("Failed to create " + tmpDirPath);
		}
		return tempDir;
	}

	/**
	 * Gets the file contents.
	 *
	 * @param filename
	 *            the filename
	 * @return the file contents
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getFileContents(final String filename) throws IOException {
		final StringBuilder buf = new StringBuilder();
		try (final FileReader in = new FileReader(filename);
		        final BufferedReader br = new BufferedReader(in);) {
			String line;
			while ((line = br.readLine()) != null) {
				buf.append(line);
				buf.append(FileUtils.CARRIAGE_RETURN);
			}
			return buf.toString().trim();
		}
	}

	/**
	 * Write string to file.
	 *
	 * @param fileName
	 *            the file name
	 * @param contents
	 *            the contents
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void writeStringToFile(final String fileName, final String contents)
	        throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			final String[] lines = contents.split(FileUtils.CARRIAGE_RETURN);
			for (final String line : lines) {
				writer.write(line);
				writer.newLine();
			}
		}
	}

	/**
	 * Instantiates a new file utils.
	 */
	private FileUtils() {
		throw new UnsupportedOperationException();
	}

}
