package com.leonarduk.core.config;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ConfigTest {

	private Config config;

	@Before
	public void setUp() throws Exception {
		config = new Config("test.properties");
	}

	@Test(expected = FileNotFoundException.class)
	public final void testConfig() throws IOException {
		new Config("missing.test.properties");
	}

	@Test
	public final void testGetProperty() {
		assertEquals("eggs and bacon", config.getProperty("test.string"));
	}

	@Test
	public final void testGetArrayProperty() {
		String[] expected = new String[] { "one", "two", "three" };
		assertArrayEquals(expected,
				config.getArrayProperty("test.string.array"));
	}

	@Test
	public final void testGetIntegerProperty() {
		final int expected = 12;
		assertEquals(expected, config.getIntegerProperty("test.int"), 0);
	}

	@Test
	public final void testGetDoubleProperty() {
		final double expected = 12.34;
		assertEquals(expected, config.getDoubleProperty("test.double"), 0);
	}

	@Test
	public final void testGetMissingProperty() {
		assertNull(config.getProperty("test.string.missing"));
	}

	@Test
	public final void testGetMissingIntegerProperty() {
		assertNull(config.getIntegerProperty("test.int.missing"));
	}

	@Test
	public final void testGetMissingDoubleProperty() {
		assertNull(config.getDoubleProperty("test.double.missing"));
	}

	@Test(expected = NumberFormatException.class)
	public final void testGetInvalidIntegerProperty() {
		assertNull(config.getIntegerProperty("test.string"));
	}

	@Test(expected = NumberFormatException.class)
	public final void testGetInvalidDoubleProperty() {
		assertNull(config.getDoubleProperty("test.string"));
	}
}
