package com.leonarduk.web;

import org.jsoup.nodes.Element;

public class JsoupClickableElement implements ClickableElement {

	private Element element;

	public JsoupClickableElement(Element element) {
		this.element = element;
	}
	
	@Override
	public void click() {
		this.element.baseUri();

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendKeys(String answer) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getAttribute(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
