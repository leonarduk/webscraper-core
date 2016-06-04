/**
 * EmailMessageBuilder
 *
 * @author ${author}
 * @since 04-Jun-2016
 */
package com.leonarduk.webscraper.core.email;

import java.util.Date;

public interface EmailMessageBuilder {

	void addContent(String content);

	void addFile(String fileName);

	EmailMessage create();

	void setSender(String sender);

	void setSentDate(Date sentDate);

	void setSubject(String subject);

}
