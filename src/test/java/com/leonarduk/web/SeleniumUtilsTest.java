/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;

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

	/**
	 * Test constructor.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = InvocationTargetException.class)
	public final void testConstructor() throws Exception {
		final Constructor<SeleniumUtils> c = SeleniumUtils.class.getDeclaredConstructor();
		c.setAccessible(true);
		c.newInstance();
	}

//	/**
//	 * Test get download capable browser invalid dir file.
//	 *
//	 * @throws IOException
//	 *             Signals that an I/O exception has occurred.
//	 */
//	@Test(expected = FileNotFoundException.class)
//	public final void testGetDownloadCapableBrowserInvalidDirFile() throws IOException {
//		SeleniumUtils.getDownloadCapableBrowser(new File("invalidpath"), true);
//	}
//
//	/**
//	 * Test get download capable browser invalid dir string.
//	 *
//	 * @throws IOException
//	 *             Signals that an I/O exception has occurred.
//	 */
//	@Test(expected = FileNotFoundException.class)
//	public final void testGetDownloadCapableBrowserInvalidDirString() throws IOException {
//		Assert.assertNotNull(SeleniumUtils.getDownloadCapableBrowser("invalidpath", true));
//	}

}
