/**
 * EmailSession
 *
 * @author ${author}
 * @since 04-Jun-2016
 */
package com.leonarduk.webscraper.core.email;

import javax.mail.Authenticator;
import javax.mail.Session;

public interface EmailSession {

	Session createSession(String user, String password, String server, String port);

	Authenticator getAuthenticator(String userName, String password);

	Session getSession();

}
