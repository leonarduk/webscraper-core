package com.leonarduk.core.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 
 * 
 *
 * @author Stephen Leonard
 * @since 24 Jan 2015
 *
 * @version $Author:: $: Author of last commit
 * @version $Rev:: $: Revision of last commit
 * @version $Date:: $: Date of last commit
 *
 */
public class Config {

	/**
	 * 
	 */
	private Properties props;

	/**
	 * 
	 * @throws IOException
	 */
	public Config(String propFileName) throws IOException {
		this.props = new Properties();

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream != null) {
			props.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}

	}

	/**
	 * 
	 * @param fieldName
	 * @return
	 */
	public String getProperty(String fieldName) {
		return this.props.getProperty(fieldName);
	}

	/**
	 * 
	 * @param fieldName
	 * @return
	 */
	public String[] getArrayProperty(String fieldName) {
		String value = getProperty(fieldName);
		if (null == value)
			return null;
		return value.replaceAll("\"", "").split("\\s*,\\s*");
	}

	/**
	 * 
	 * @param fieldName
	 * @return
	 * @throws NumberFormatException
	 */
	public Integer getIntegerProperty(String fieldName)
			throws NumberFormatException {
		String value = getProperty(fieldName);
		if (null == value)
			return null;
		return Integer.valueOf(value);
	}

	/**
	 * 
	 * @param fieldName
	 * @return
	 * @throws NumberFormatException
	 */
	public Double getDoubleProperty(String fieldName)
			throws NumberFormatException {
		String value = getProperty(fieldName);
		if (null == value)
			return null;
		return Double.valueOf(value);
	}
}