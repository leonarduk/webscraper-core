/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.web;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;

/**
 * The Class BaseSeleniumPage.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev: $: Revision of last commit
 * @version $Date$: Date of last
 *          commit
 * @since 18 Feb 2015
 */
public abstract class BaseSeleniumPage extends
        LoadableComponent<BaseSeleniumPage> {
    /** The web driver. */
    private final WebDriver webdriver;

    private final String expectedUrl;

    private boolean acceptNextAlert = true;

    /** The Constant ONE_SECOND_IN_MS. */
    public static final int ONE_SECOND_IN_MS = 1000;

    /** The Constant LOGGER. */
    static final Logger LOGGER = Logger.getLogger(BaseSeleniumPage.class);

    /**
     * Instantiates a new base selenium page.
     *
     * @param webDriver
     *            the web driver
     * @param expectedUrl
     *            the expected url
     */
    public BaseSeleniumPage(final WebDriver webDriver, final String expectedUrl) {
        super();
        this.expectedUrl = expectedUrl;
        this.webdriver = webDriver;
    }

    /**
     * Gets the expected url.
     *
     * @return the expected url
     */
    public final String getExpectedUrl() {
        return expectedUrl;
    }

    /**
     * Click field.
     *
     * @param xpath
     *            the xpath
     */
    protected final void clickField(final String xpath) {
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
    protected final WebElement enterValueIntoField(
            final String answer,
            final String xpath) {
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
    protected final WebElement findElementByXpath(final String xpath) {
        final WebElement findElement =
                this.webdriver.findElement(By.xpath(xpath));
        if (null == findElement) {
            throw new NoSuchElementException("Could not find xpath " + xpath);
        }
        return findElement;

    }

    /**
     * Gets the web driver.
     *
     * @return the web driver
     */
    public final WebDriver getWebDriver() {
        return this.webdriver;
    }

    /**
     * Keep number only.
     *
     * @param password2
     *            the password2
     * @return the string
     */
    protected final int keepNumberOnly(final String password2) {
        return Integer.valueOf(password2.replaceAll("\\D+", ""));
    }

    public boolean isElementPresent(By by) {
        try {
            this.getWebDriver().findElement(by);
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            this.getWebDriver().switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException e) {
            return false;
        }
    }

    public String closeAlertAndGetItsText() {
        try {
            Alert alert = this.getWebDriver().switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            }
            else {
                alert.dismiss();
            }
            return alertText;
        }
        finally {
            acceptNextAlert = true;
        }
    }

    /**
     * Wait for page to load.
     */
    protected final void waitForPageToLoad() {
        try {
            final int halfASecond = 500;
            Thread.sleep(halfASecond);
        }
        catch (final InterruptedException e) {
            LOGGER.info("Interrupted");
        }
    }

    @Override
    protected final void isLoaded() {
        if (!this.getWebDriver().getCurrentUrl().equals(this.expectedUrl)) {
            this.load();
        }
        String url = this.getWebDriver().getCurrentUrl();
        if (!url.startsWith(this.expectedUrl)) {
            throw new RuntimeException(this.expectedUrl
                                       + " is  not loaded. Instead is " + url);
        }

    }
}
