package com.leonarduk.web;

import java.io.IOException;

public class SeleniumException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public SeleniumException(final String string) {
		super(string);
	}

	public SeleniumException(String string, IOException e) {
		super(string, e);
	}

}
