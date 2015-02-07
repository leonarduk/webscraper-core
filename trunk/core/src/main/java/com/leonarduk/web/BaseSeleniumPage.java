/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.web;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class BaseSeleniumPage extends LoadableComponent<BaseSeleniumPage> {
	/** The web driver. */
	private final WebDriver webDriver;
	static final Logger LOGGER = Logger.getLogger(BaseSeleniumPage.class);

	public BaseSeleniumPage(final WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}

	/**
	 * Click field.
	 *
	 * @param xpath
	 *            the xpath
	 */
	protected void clickField(final String xpath) {
		this.findElementByXpath(xpath).click();
	}

	/**
	 * Enter value into field.
	 *
	 * @param answer
	 *            the answer
	 * @param xpath
	 *            the xpath
	 * @return the web element
	 */
	protected WebElement enterValueIntoField(final String answer, final String xpath) {
		final WebElement element = this.findElementByXpath(xpath);
		element.clear();
		element.sendKeys(answer);
		return element;

	}

	/**
	 * Find element by xpath.
	 *
	 * @param xpath
	 *            the xpath
	 * @return the web element
	 */
	protected WebElement findElementByXpath(final String xpath) {
		try {
			return this.webDriver.findElement(By.xpath(xpath));
		}
		catch (final NoSuchElementException e) {
			BaseSeleniumPage.LOGGER.warn("Could not find " + xpath);
			this.waitForPageToLoad();
			return this.webDriver.findElement(By.xpath(xpath));

		}
	}

	/**
	 * Gets the web driver.
	 *
	 * @return the web driver
	 */
	public WebDriver getWebDriver() {
		return this.webDriver;
	}

	/**
	 * Keep number only.
	 *
	 * @param password2
	 *            the password2
	 * @return the string
	 */
	protected int keepNumberOnly(final String password2) {
		return Integer.valueOf(password2.replaceAll("\\D+", ""));
	}

	/**
	 * Wait for page to load.
	 */
	protected void waitForPageToLoad() {
		try {
			Thread.sleep(500);
		}
		catch (final InterruptedException e) {
		}
	}

}
