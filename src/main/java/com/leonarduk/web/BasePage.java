package com.leonarduk.web;

import java.io.IOException;
import java.util.List;

public abstract class BasePage<T> implements AutoCloseable {
	public static final int ONE_SECOND_IN_MS = 1000;

	private final BrowserController webDriver;
	private final String expectedUrl;

	public BasePage(BrowserController webDriver, String expectedUrl) {
		this.webDriver = webDriver;
		this.expectedUrl = expectedUrl;
	}

	@Override
	public void close() throws Exception {
		this.webDriver.close();
	}

	protected List<ClickableElement> findElementsByXpath(String url) throws IOException {
		return this.webDriver.findElementsByXpath(url);
	}

	protected ClickableElement findElementByXpath(String url) throws IOException {
		return this.webDriver.findElementByXpath(url);
	}

	/**
	 * Keep number only.
	 *
	 * @param password2 the password2
	 * @return the string
	 */
	protected final int keepNumberOnly(final String password2) {
		return Integer.valueOf(password2.replaceAll("\\D+", "")).intValue();
	}

	@SuppressWarnings("unchecked")
	public T get() throws IOException {
		try {
			isLoaded();
			return (T) this;
		} catch (Error e) {
			load();
		}

		isLoaded();

		return (T) this;
	}

	/**
	 * Enter value into field.
	 *
	 * @param answer the answer
	 * @param xpath  the xpath
	 * @return the web element
	 * @throws IOException
	 */
	protected final ClickableElement enterValueIntoField(final String answer, final String xpath) throws IOException {
		final ClickableElement element = this.findElementByXpath(xpath);
		element.clear();
		element.sendKeys(answer);
		return element;

	}

	protected ClickableElement findElementById(String url) {
		return this.webDriver.findElementById(url);
	}

	public BrowserController getBrowserController() {
		return webDriver;
	}

	protected void clickField(String passwordSubmitXpath) throws IOException {
		this.findElementByXpath(passwordSubmitXpath).click();
	}

	public String getExpectedUrl() {
		return this.expectedUrl;
	}

	public void isLoaded() {
		if (!this.webDriver.getCurrentUrl().equals(this.expectedUrl)) {
			try {
				this.load();
			} catch (IOException e) {
				throw new SeleniumException(this.expectedUrl + " is  not loaded. ", e);
			}
		}
		final String url = this.webDriver.getCurrentUrl();
		if (!url.startsWith(this.expectedUrl)) {
			throw new SeleniumException(this.expectedUrl + " is  not loaded. Instead is " + url);
		}
	}

	protected abstract void load() throws IOException;

	protected void selectFrame(int i) {
		this.webDriver.selectFrame(i);
	}

	protected void waitForPageToLoad() {
		this.webDriver.waitForPageToLoad();
	}

}
