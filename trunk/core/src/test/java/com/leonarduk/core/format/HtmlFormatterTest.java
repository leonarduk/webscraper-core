/**
 * 
 */
package com.leonarduk.core.format;

import static org.junit.Assert.assertEquals;

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

    HtmlFormatter formatter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.formatter = new HtmlFormatter();
    }

    /**
     * Test method for
     * {@link com.leonarduk.core.format.HtmlFormatter#formatHeader(java.lang.String)}
     * .
     */
    @Test
    public final void testFormatHeader() {
        assertEquals("<h1>header</h1>", this.formatter.formatHeader("header"));
    }

    /**
     * Test method for
     * {@link com.leonarduk.core.format.HtmlFormatter#formatLink(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public final void testFormatLink() {
        assertEquals("<a href=\"http://link\">link text</a>",
                this.formatter.formatLink("http://link", "link text"));
    }

    /**
     * Test method for
     * {@link com.leonarduk.core.format.HtmlFormatter#formatSubHeader(java.lang.String)}
     * .
     */
    @Test
    public final void testFormatSubHeader() {
        assertEquals("<h2>header</h2>",
                this.formatter.formatSubHeader("header"));
    }

    /**
     * Test method for
     * {@link com.leonarduk.core.format.HtmlFormatter#getNewLine()}.
     */
    @Test
    public final void testGetNewLine() {
        assertEquals("<br/>", this.formatter.getNewLine());
    }

    /**
     * Test method for
     * {@link com.leonarduk.core.format.HtmlFormatter#getNewSection()}.
     */
    @Test
    public final void testGetNewSection() {
        assertEquals("<hr/>", this.formatter.getNewSection());
    }

    /**
     * Test method for
     * {@link com.leonarduk.core.format.HtmlFormatter#formatBody(java.lang.String)}
     * .
     */
    @Test
    public final void testFormatBody() {
        String text = "test\ntest2\ntest3";
        assertEquals("test<br/>test2<br/>test3",
                this.formatter.formatBody(text));
    }

}
