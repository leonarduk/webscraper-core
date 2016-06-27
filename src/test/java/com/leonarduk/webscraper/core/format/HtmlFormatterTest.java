/**
 *
 */
package com.leonarduk.webscraper.core.format;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 *
 * @author stephen
 * @since 7 May 2015
 *
 * @version $Author: $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date: $: Date of last commit
 *
 */
public class HtmlFormatterTest {

	private HtmlFormatter formatter;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.formatter = new HtmlFormatter();
	}

	/**
	 * Test method for
	 * {@link com.leonarduk.webscraper.core.format.HtmlFormatter#formatBody(java.lang.String)} .
	 */
	@Test
	public final void testFormatBody() {
		final String text = "test\ntest2\ntest3";
		Assert.assertEquals("test<br/>test2<br/>test3", this.formatter.formatBody(text));
	}

	/**
	 * Test method for
	 * {@link com.leonarduk.webscraper.core.format.HtmlFormatter#formatHeader(java.lang.String)} .
	 */
	@Test
	public final void testFormatHeader() {
		Assert.assertEquals("<h1>header</h1>", this.formatter.formatHeader("header"));
	}

	/**
	 * Test method for
	 * {@link com.leonarduk.webscraper.core.format.HtmlFormatter#formatLink(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testFormatLink() {
		Assert.assertEquals("<a href=\"http://link\">link text</a>",
		        this.formatter.formatLink("http://link", "link text"));
	}

	@Test
	public final void testFormatSmall() {
		final String text = "test";
		Assert.assertEquals("<h3 style=\"font-size:70%;\">test</h3>",
		        this.formatter.formatSmall(text));
	}

	/**
	 * Test method for
	 * {@link com.leonarduk.webscraper.core.format.HtmlFormatter#formatSubHeader(java.lang.String)}
	 * .
	 */
	@Test
	public final void testFormatSubHeader() {
		Assert.assertEquals("<h2>header</h2>", this.formatter.formatSubHeader("header"));
	}

	/**
	 * Test method for {@link com.leonarduk.webscraper.core.format.HtmlFormatter#getNewLine()}.
	 */
	@Test
	public final void testGetNewLine() {
		Assert.assertEquals("<br/>", this.formatter.getNewLine());
	}

	/**
	 * Test method for {@link com.leonarduk.webscraper.core.format.HtmlFormatter#getNewSection()}.
	 */
	@Test
	public final void testGetNewSection() {
		Assert.assertEquals("<hr/>", this.formatter.getNewSection());
	}

}
