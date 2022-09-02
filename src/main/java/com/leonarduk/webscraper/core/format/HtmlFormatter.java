/*
 *
 */
package com.leonarduk.webscraper.core.format;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * The Class HtmlFormatter.
 *
 * @author Stephen Leonard
 * @version $Author:: $: Author of last commit
 * @version $Rev:: $: Revision of last commit
 * @version $Date:: $: Date of last commit
 * @since 28 Jan 2015
 */
public class HtmlFormatter implements Formatter {
	private static final Logger LOGGER = LogManager.getLogger(HtmlFormatter.class);

	public String addHeader(final String value) {
		return this.createNode(value, "th");
	}

	public String addValue(final String value) {
		return this.createNode(value, "td");
	}

	/**
	 * Creates the node.
	 *
	 * @param value the value
	 * @param node  the node
	 * @return the string
	 */
	private String createNode(final String value, final String node) {
		return new StringBuilder("<").append(node).append(">").append(value).append("</").append(node).append(">")
				.toString();
	}

	private String createNode(final String value, final String nodeName, final String attributes) {
		return new StringBuilder("<").append(nodeName).append(" ").append(attributes).append(">").append(value)
				.append("</").append(nodeName).append(">").toString();
	}

	@Override
	public String endFile() {
		return new StringBuilder("</body></html>").toString();
	}

	@Override
	public String formatBody(final String text) {
		HtmlFormatter.LOGGER.info("Format: " + text);
		return text.replaceAll("\n", this.getNewLine());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.leonarduk.itemfinder.format.Formatter#formatHeader(java.lang.String)
	 */
	@Override
	public final String formatHeader(final String header) {
		return this.createNode(header, "h1");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.leonarduk.itemfinder.format.Formatter#formatLink(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public final String formatLink(final String link, final String name) {
		final StringBuilder linkBuilder = new StringBuilder("<a href=\"" + link + "\">");
		linkBuilder.append(name);
		linkBuilder.append("</a>");
		return linkBuilder.toString();
	}

	@Override
	public final String formatSmall(final String note) {
		return this.createNode(note, "h3", "style=\"font-size:70%;\"");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.leonarduk.itemfinder.format.Formatter#formatSubHeader(java.lang.String )
	 */
	@Override
	public final String formatSubHeader(final String header) {
		return this.createNode(header, "h2");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.leonarduk.itemfinder.format.Formatter#getNewLine()
	 */
	@Override
	public final String getNewLine() {
		return "<br/>";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.leonarduk.itemfinder.format.Formatter#getNewSection()
	 */
	@Override
	public final String getNewSection() {
		return "<hr/>";
	}

	@Override
	public boolean isHtml() {
		return true;
	}

	@Override
	public String startFile() {
		return new StringBuilder("<html><body>").toString();
	}
}
