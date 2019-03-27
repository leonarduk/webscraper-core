package com.leonarduk.web;

import org.openqa.selenium.WebElement;

public class SeleniumClickableElement implements ClickableElement {

	private WebElement element;

	public SeleniumClickableElement(WebElement element) {
		this.element = element;
	}

	@Override
	public void click() {
		this.element.click();
	}

	@Override
	public String getText() {
		return this.element.getText();
	}

	@Override
	public void clear() {
		this.element.clear();
	}

	@Override
	public void sendKeys(String answer) {
		this.element.sendKeys(answer);
	}

	@Override
	public String getAttribute(String string) {
		return this.element.getAttribute(string);
	}

}
