package com.leonarduk.web;

public interface ClickableElement {

	void click();

	String getText();

	void clear();

	void sendKeys(String answer);

	String getAttribute(String string);

}
