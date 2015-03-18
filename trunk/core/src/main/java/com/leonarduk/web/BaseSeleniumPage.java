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

    /** The Constant ONE_SECOND_IN_MS. */
    public static final int ONE_SECOND_IN_MS = 1000;

    /** The Constant LOGGER. */
    static final Logger LOGGER = Logger.getLogger(BaseSeleniumPage.class);

    /**
     * Instantiates a new base selenium page.
     *
     * @param webDriver
     *            the web driver
     */
    public BaseSeleniumPage(final WebDriver webDriver) {
        super();
        this.webdriver = webDriver;
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

}
