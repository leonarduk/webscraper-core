/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.webscraper.core.config;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class ConfigTest.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev$: Revision of last commit
 * @version $Date: 2015-02-08 21:26:51 +0000 (Sun, 08 Feb 2015) $: Date of last commit
 * @since 8 Feb 2015
 */
public class ConfigTest {

	/** The config. */
	private Config config;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public final void setUp() throws Exception {
		this.config = new Config("test.properties");
	}

	/**
	 * Test config.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unused")
	@Test(expected = FileNotFoundException.class)
	public final void testConfig() throws IOException {
		new Config("missing.test.properties");
	}

	/**
	 * Test get array property.
	 */
	@Test
	public final void testGetArrayProperty() {
		final String[] expected = new String[] { "one", "two", "three" };
		Assert.assertArrayEquals(expected, this.config.getArrayProperty("test.string.array"));
	}

	/**
	 * Test get boolean property.
	 */
	@Test
	public final void testGetBooleanProperty() {
		final boolean expected = true;
		Assert.assertEquals(Boolean.valueOf(expected),
		        Boolean.valueOf(this.config.getBooleanProperty("test.bool")));
	}

	/**
	 * Test get double property.
	 */
	@Test
	public final void testGetDoubleProperty() {
		final double expected = 12.34;
		Assert.assertEquals(expected, this.config.getDoubleProperty("test.double").doubleValue(),
		        0);
	}

	/**
	 * Test get integer property.
	 */
	@Test
	public final void testGetIntegerProperty() {
		final int expected = 12;
		Assert.assertEquals(expected, this.config.getIntegerProperty("test.int").intValue(), 0);
	}

	/**
	 * Test get invalid double property.
	 */
	@Test(expected = NumberFormatException.class)
	public final void testGetInvalidDoubleProperty() {
		Assert.assertNull(this.config.getDoubleProperty("test.string"));
	}

	/**
	 * Test get invalid integer property.
	 */
	@Test(expected = NumberFormatException.class)
	public final void testGetInvalidIntegerProperty() {
		Assert.assertNull(this.config.getIntegerProperty("test.string"));
	}

	/**
	 * Test get missing double property.
	 */
	@Test
	public final void testGetMissingDoubleProperty() {
		Assert.assertNull(this.config.getDoubleProperty("test.double.missing"));
	}

	/**
	 * Test get missing integer property.
	 */
	@Test
	public final void testGetMissingIntegerProperty() {
		Assert.assertNull(this.config.getIntegerProperty("test.int.missing"));
	}

	/**
	 * Test get missing property.
	 */
	@Test
	public final void testGetMissingProperty() {
		Assert.assertNull(this.config.getProperty("test.string.missing"));
	}

	/**
	 * Test get null array property.
	 */
	@Test
	public final void testGetNullArrayProperty() {
		Assert.assertNull(this.config.getArrayProperty("test.string.array.missing"));
	}

	/**
	 * Test get null boolean property.
	 */
	@Test
	public final void testGetNullBooleanProperty() {
		Assert.assertFalse(this.config.getBooleanProperty("test.bool.missing"));
	}

	/**
	 * Test get property.
	 */
	@Test
	public final void testGetProperty() {
		Assert.assertEquals("eggs and bacon", this.config.getProperty("test.string"));
	}

	@Test
	public final void testGetSystemIntegerProperty() {
		final int expected = 8;
		System.setProperty("test.int", "8");
		Assert.assertEquals(expected, this.config.getIntegerProperty("test.int").intValue(), 0);
		System.setProperty("test.int", "");
		Assert.assertEquals(12, this.config.getIntegerProperty("test.int").intValue(), 0);
	}

	@Test
	public final void testGetSystemProperty() {
		final String fieldName = "test.string";
		System.setProperty(fieldName, "veggie");
		Assert.assertEquals("veggie", this.config.getProperty(fieldName));
		System.setProperty(fieldName, "");
		Assert.assertEquals("eggs and bacon", this.config.getProperty(fieldName));
	}

	/**
	 * Test set property.
	 */
	@Test
	public final void testSetProperty() {
		final String value = "specificValue";
		final String key = "Random";
		this.config.setProperty(key, value);
		Assert.assertEquals(value, this.config.getProperty(key));
	}
}
