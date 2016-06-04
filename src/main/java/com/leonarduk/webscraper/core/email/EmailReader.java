/**
 * EmailReader
 * 
 * @author ${author}
 * @since 04-Jun-2016
 */
package com.leonarduk.webscraper.core.email;

public interface EmailReader {

	void processMail(String server, String userName, String password, ServerType serverType,
	        String attachmentsDirectory, EmailProcessor emailProcessor);

}
