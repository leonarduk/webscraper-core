///**
// * All rights reserved. @Leonard UK Ltd.
// */
//package com.leonarduk.web;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
///**
// * The Class BaseSeleniumPageTest.
// */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ Thread.class })
///**
// *
// *
// *
// * @author stephen
// * @since 18 Mar 2015
// *
// * @version $Author: $: Author of last commit
// * @version $Rev: $: Revision of last commit
// * @version $Date: $: Date of last commit
// *
// */
//public class BaseSeleniumPageTest {
//
//	/** The mock driver. */
//	private WebDriver mockDriver;
//
//	/** The test class. */
//	private BaseSeleniumPage testClass;
//
//	/** The xpath. */
//	private String xpath;
//
//	/** The mock web element. */
//	private WebElement mockWebElement;
//
//	/** The test attribute. */
//	private String testAttribute;
//
//	/**
//	 * Sets the up.
//	 *
//	 * @throws Exception
//	 *             the exception
//	 */
//	@Before
//	public final void setUp() throws Exception {
//		this.mockDriver = Mockito.mock(WebDriver.class);
//		this.testClass = new BaseSeleniumPage(this.mockDriver, this.testAttribute) {
//
//			@Override
//			protected void load() {
//				//
//			}
//		};
//
//		this.xpath = "xpath";
//		this.mockWebElement = Mockito.mock(WebElement.class);
//		this.testAttribute = "TEST";
//		Mockito.when(this.mockWebElement.getAttribute(this.testAttribute)).thenReturn("testValue");
//		Mockito.when(this.mockDriver.findElement(By.xpath(this.xpath)))
//		        .thenReturn(this.mockWebElement);
//
//	}
//
//	/**
//	 * Test base selenium page.
//	 */
//	@Test
//	public final void testBaseSeleniumPage() {
//		Assert.assertNotNull(this.testClass);
//	}
//
//	/**
//	 * Test click field.
//	 */
//	@Test
//	public final void testClickField() {
//		try {
//			this.testClass.clickField(this.xpath);
//		}
//		catch (final NoSuchElementException e) {
//			System.out.println(e);
//			Assert.fail("Should not get this error");
//		}
//	}
//
//	/**
//	 * Test enter value into field.
//	 */
//	@Test
//	public final void testEnterValueIntoField() {
//		try {
//			final String xpathString = "xpath";
//			this.testClass.enterValueIntoField("test", xpathString);
//		}
//		catch (final NoSuchElementException e) {
//			System.out.println(e);
//			Assert.fail("Should not get this error");
//		}
//	}
//
//	/**
//	 * Test find element by invalid xpath.
//	 */
//	@Test(expected = NoSuchElementException.class)
//	public final void testFindElementByInvalidXpath() {
//		this.testClass.findElementByXpath("DUMMY");
//	}
//
//	/**
//	 * Test find element by xpath.
//	 */
//	@Test
//	public final void testFindElementByXpath() {
//		final WebElement actual = this.testClass.findElementByXpath(this.xpath);
//
//		Assert.assertEquals(this.mockWebElement.getAttribute(this.testAttribute),
//		        actual.getAttribute(this.testAttribute));
//	}
//
//	/**
//	 * Test get web driver.
//	 */
//	@Test
//	public final void testGetWebDriver() {
//		Assert.assertEquals(this.mockDriver, this.testClass.getWebDriver());
//	}
//
//	/**
//	 * Test keep number only.
//	 */
//	@Test
//	public final void testKeepNumberOnly() {
//		final int expected = 234;
//		Assert.assertEquals(expected, this.testClass.keepNumberOnly("234dsf"));
//	}
//
//}
