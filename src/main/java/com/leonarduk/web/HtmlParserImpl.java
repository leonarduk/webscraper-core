/**
 *
 */
package com.leonarduk.web;

import java.net.HttpURLConnection;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * Decorator class to allow later modifications.
 *
 * @author Stephen Leonard
 * @version $Author:: $: Author of last commit
 * @version $Rev:: $: Revision of last commit
 * @version $Date:: $: Date of last commit
 * @since 23 Jan 2015
 */
public class HtmlParserImpl extends Parser implements HtmlParser {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8969008563666070296L;

	/**
	 * Instantiates a new html parser.
	 *
	 * @param connection
	 *            the connection
	 * @throws ParserException
	 *             the parser exception
	 */
	public HtmlParserImpl(final HttpURLConnection connection) throws ParserException {
		super(connection);
	}

	/**
	 * Instantiates a new html parser.
	 *
	 * @param url
	 *            the url
	 * @throws ParserException
	 *             the parser exception
	 */
	public HtmlParserImpl(final String url) throws ParserException {
		super(url);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.htmlparser.Parser#extractAllNodesThatMatch(org.htmlparser.NodeFilter)
	 */
	@Override
	public final NodeList extractAllNodesThatMatch(final NodeFilter filter) throws ParserException {
		return super.extractAllNodesThatMatch(filter);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.htmlparser.Parser#getURL()
	 */
	@Override
	public final String getURL() {
		return super.getURL();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.htmlparser.Parser#parse(org.htmlparser.NodeFilter)
	 */
	@Override
	public final NodeList parse(final NodeFilter filter) throws ParserException {
		return super.parse(filter);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.htmlparser.Parser#setURL(java.lang.String)
	 */
	@Override
	public final void setURL(final String url) throws ParserException {
		super.setURL(url);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return this.getURL();
	}
}
