package com.leonarduk.web;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumBrowserController implements BrowserController {
	private static final Logger LOGGER = Logger.getLogger(SeleniumBrowserController.class);

	private WebDriver webDriver;

	public SeleniumBrowserController(final WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	@Override
	public void waitForPageToLoad() {
		try {
			final int halfASecond = 500;
			Thread.sleep(10 * halfASecond);
			if (!SeleniumUtils.isInternetAvailable()) {
				LOGGER.warn("no internet - can't load " + webDriver.getCurrentUrl());
				BaseSeleniumPage.waitForPageToLoad(webDriver);
				webDriver.get(webDriver.getCurrentUrl());
			}
		} catch (final InterruptedException e) {
			LOGGER.info("Interrupted", e);
		}
	}

	@Override
	public void selectFrame(int i) {
		this.webDriver.switchTo().frame(i);
	}

	@Override
	public void close() throws IOException {
		this.webDriver.close();
	}

	@Override
	public ClickableElement findElementByXpath(String url) {
		try {
			return new SeleniumClickableElement(this.webDriver.findElement(By.xpath(url)));
		} catch (org.openqa.selenium.NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());
		}

	}

	@Override
	public String getCurrentUrl() {
		return this.webDriver.getCurrentUrl();
	}

	@Override
	public void get(String loginUrl) {
		this.webDriver.get(loginUrl);
	}

	@Override
	public List<ClickableElement> findElementsByXpath(String url) {
		return this.webDriver.findElements(By.xpath(url)).stream().map(SeleniumClickableElement::new)
				.collect(Collectors.toList());
	}

	@Override
	public ClickableElement findElementById(String url) {
		return new SeleniumClickableElement(this.webDriver.findElement(By.id(url)));
	}

}
