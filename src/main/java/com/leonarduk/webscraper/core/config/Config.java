/**
 *
 */
package com.leonarduk.webscraper.core.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The Class Config.
 *
 * @author Stephen Leonard
 * @version $Author: $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date$: Date of last commit
 * @since 24 Jan 2015
 */
public class Config {

	/** The props. */
	private final Properties props;

	/**
	 * Instantiates a new empty config .
	 */
	public Config() {
		this.props = new Properties();
	}

	/**
	 * Instantiates a new config.
	 *
	 * @param propFileName
	 *            the prop file name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public Config(final String propFileName) throws IOException {
		this();
		final InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream != null) {
			this.props.load(inputStream);
		}
		else {
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}

	}

	/**
	 * Gets the array property.
	 *
	 * @param fieldName
	 *            the field name
	 * @return the array property
	 */
	public final String[] getArrayProperty(final String fieldName) {
		final String value = this.getProperty(fieldName);
		if (null == value) {
			return null;
		}
		return value.replaceAll("\"", "").split("\\s*,\\s*");
	}

	/**
	 * Gets the boolean property.
	 *
	 * @param fieldName
	 *            the field name
	 * @return the boolean property
	 */
	public final boolean getBooleanProperty(final String fieldName) {
		final String value = this.getProperty(fieldName);
		if (null == value) {
			return false;
		}
		return Boolean.valueOf(value);
	}

	/**
	 * Gets the double property.
	 *
	 * @param fieldName
	 *            the field name
	 * @return the double property
	 */
	public final Double getDoubleProperty(final String fieldName) {
		final String value = this.getProperty(fieldName);
		if (null == value) {
			return null;
		}
		return Double.valueOf(value);
	}

	/**
	 * Gets the integer property.
	 *
	 * @param fieldName
	 *            the field name
	 * @return the integer property
	 */
	public final Integer getIntegerProperty(final String fieldName) {
		final String value = this.getProperty(fieldName);
		if (null == value) {
			return null;
		}
		return Integer.valueOf(value);
	}

	/**
	 * Gets the property.
	 *
	 * @param fieldName
	 *            the field name
	 * @return the property
	 */
	public final String getProperty(final String fieldName) {
		return this.props.getProperty(fieldName);
	}

	/**
	 * Sets the property.
	 *
	 * @param fieldName
	 *            the field name
	 * @param value
	 *            the value
	 */
	public final void setProperty(final String fieldName, final String value) {
		this.props.setProperty(fieldName, value);
	}
}
