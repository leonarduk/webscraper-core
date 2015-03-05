/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Class FileUtils.
 *
 * @author stephen
 */
public final class FileUtils {

    /**
     * Gets the file contents.
     *
     * @param filename
     *            the filename
     * @return the file contents
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static String getFileContents(final String filename)
            throws IOException {
        final StringBuilder buf = new StringBuilder();
        final FileReader in = new FileReader(filename);
        final BufferedReader br = new BufferedReader(in);

        String line;
        while ((line = br.readLine()) != null) {
            buf.append(line);
        }
        in.close();
        return buf.toString().trim();
    }

    /**
     * Instantiates a new file utils.
     */
    private FileUtils() {
    }
}
