package com.leonarduk.web;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.NoSuchElementException;

import com.google.common.collect.Lists;

import us.codecraft.xsoup.Xsoup;

public class JSoupBrowserController implements BrowserController {
	private final Logger LOG = Logger.getLogger(JSoupBrowserController.class);
	private Document doc;

	public JSoupBrowserController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public void waitForPageToLoad() {
	}

	@Override
	public void selectFrame(int i) {
		// TODO Auto-generated method stub

	}

	@Override
	public ClickableElement findElementByXpath(String url) throws IOException {
		Elements elements = Xsoup.compile(url).evaluate(doc).getElements();
		if (elements.isEmpty()) {
			throw new NoSuchElementException("Failed to find xpath: " + url + " in " + doc.html());
		}
		return new JsoupClickableElement(elements.get(0));
	}

	@Override
	public String getCurrentUrl() {
		if (doc == null) {
			return "NOT LOADED";
		}
		return doc.baseUri();
	}

	@Override
	public void get(String loginUrl) throws IOException {
		LOG.info("Extracting details for " + loginUrl);
		doc = Jsoup.connect(loginUrl).get();
	}

	@Override
	public List<ClickableElement> findElementsByXpath(String url) throws IOException {
		List<ClickableElement> elements = Lists.newLinkedList();
		ClickableElement jsoupClickableElement;
		do {
			jsoupClickableElement = findElementByXpath(url);
			if (jsoupClickableElement != null) {
				elements.add(jsoupClickableElement);
			}
		} while (jsoupClickableElement != null);

		return elements;
	}

	@Override
	public ClickableElement findElementById(String url) {
		return new JsoupClickableElement(doc.getElementById(url));
	}

}
