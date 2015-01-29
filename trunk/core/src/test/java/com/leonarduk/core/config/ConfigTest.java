/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.config;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class ConfigTest.
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
	 * Test get double property.
	 */
	@Test
	public final void testGetDoubleProperty() {
		final double expected = 12.34;
		Assert.assertEquals(expected, this.config.getDoubleProperty("test.double"), 0);
	}

	/**
	 * Test get integer property.
	 */
	@Test
	public final void testGetIntegerProperty() {
		final int expected = 12;
		Assert.assertEquals(expected, this.config.getIntegerProperty("test.int"), 0);
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
	 * Test get property.
	 */
	@Test
	public final void testGetProperty() {
		Assert.assertEquals("eggs and bacon", this.config.getProperty("test.string"));
	}
}
