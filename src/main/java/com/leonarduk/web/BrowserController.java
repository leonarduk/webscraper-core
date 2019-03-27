package com.leonarduk.web;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public interface BrowserController extends Closeable {

	void waitForPageToLoad();

	void selectFrame(int i);

	ClickableElement findElementByXpath(String url) throws IOException;

	String getCurrentUrl();

	void get(String loginUrl) throws IOException;

	List<ClickableElement> findElementsByXpath(String url) throws IOException;

	ClickableElement findElementById(String url);

}
