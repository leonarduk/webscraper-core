package com.leonarduk.webscraper.core.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties; 

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;

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

	public Config(final Properties properties) {
		this.props = properties;
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
		try (final InputStream inputStream = this.getClass().getClassLoader()
		        .getResourceAsStream(propFileName);) {

			if (inputStream != null) {
				this.props.load(inputStream);
			}
			else {
				throw new FileNotFoundException(
				        "property file '" + propFileName + "' not found in the classpath");
			}
			
			for (Object key : this.props.keySet()) {
			      String override = System.getProperty((String) key);
			      if (null!= override && !override.isEmpty()) {
			        props.put(key, override);
			      }
			    }
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
		return Boolean.valueOf(value).booleanValue();
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
		final String property = System.getProperty(fieldName);
		if (StringUtils.isNotEmpty(property)) {
			return property;
		}
		return this.props.getProperty(fieldName);
	}

	public final void setArrayProperty(final String fieldName, final String[] value) {
		this.props.setProperty(fieldName, Joiner.on(",").join(value));
	}

	public final void setBooleanProperty(final String fieldName, final boolean value) {
		this.props.setProperty(fieldName, String.valueOf(value));
	}

	public final void setDoubleProperty(final String fieldName, final double value) {
		this.props.setProperty(fieldName, String.valueOf(value));
	}

	public final void setIntegerProperty(final String fieldName, final int value) {
		this.props.setProperty(fieldName, String.valueOf(value));
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
