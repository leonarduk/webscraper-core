/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
    Thread.class
})
public class BaseSeleniumPageTest {

    private WebDriver mockDriver;
    private BaseSeleniumPage testClass;
    private String xpath;
    private WebElement mockWebElement;
    private String testAttribute;

    @Before
    public void setUp() throws Exception {
        this.mockDriver = Mockito.mock(WebDriver.class);
        this.testClass = new BaseSeleniumPage(this.mockDriver) {

            @Override
            protected void isLoaded() throws Error {
                // TODO Auto-generated method stub

            }

            @Override
            protected void load() {
                // TODO Auto-generated method stub

            }
        };

        this.xpath = "xpath";
        this.mockWebElement = Mockito.mock(WebElement.class);
        this.testAttribute = "TEST";
        Mockito.when(this.mockWebElement.getAttribute(this.testAttribute))
                .thenReturn("testValue");
        Mockito.when(this.mockDriver.findElement(By.xpath(this.xpath)))
                .thenReturn(this.mockWebElement);

    }

    @Test
    public final void testBaseSeleniumPage() {
        Assert.assertNotNull(this.testClass);
    }

    @Test
    public final void testClickField() {
        this.testClass.clickField(this.xpath);
    }

    @Test
    public final void testEnterValueIntoField() {
        final String xpath = "xpath";
        this.testClass.enterValueIntoField("test", xpath);
    }

    @Test(expected = NoSuchElementException.class)
    public final void testFindElementByInvalidXpath() {
        this.testClass.findElementByXpath("DUMMY");
    }

    @Test
    public final void testFindElementByXpath() {
        final WebElement actual = this.testClass.findElementByXpath(this.xpath);

        Assert.assertEquals(
                this.mockWebElement.getAttribute(this.testAttribute),
                actual.getAttribute(this.testAttribute));
    }

    @Test
    public final void testGetWebDriver() {
        Assert.assertEquals(this.mockDriver, this.testClass.getWebDriver());
    }

    @Test
    public final void testKeepNumberOnly() {
        Assert.assertEquals(234, this.testClass.keepNumberOnly("234dsf"));
    }

    @Test
    public final void testWaitForPageToLoad() {
        this.testClass.waitForPageToLoad();
    }

    @Test
    @Ignore
    public final void testWaitForPageToLoadInterrupted()
            throws NoSuchMethodException,
            SecurityException,
            Exception {
        PowerMockito.mockStatic(Thread.class);
        PowerMockito.doThrow(new InterruptedException()).when(Thread.class,
                Thread.class.getDeclaredMethod("sleep", Long.class));

        this.testClass.waitForPageToLoad();
    }
}
