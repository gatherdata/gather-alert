/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.notify.mail.internal;

import static org.junit.Assert.fail;

import org.gatherdata.alert.core.model.impl.MutableNotification;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;

public class EmailNotifierTest {
	
	@SuppressWarnings("unchecked")
    @Test(timeout=10000)
	public void shouldBeAbleToSendPlainEmail() throws MalformedURLException, URISyntaxException, UnsupportedEncodingException, AddressException {
		final int MOCK_SMTP_PORT = 2525;
		SimpleSmtpServer server = SimpleSmtpServer.start(MOCK_SMTP_PORT );

		InternetAddress mailtoUrl = new InternetAddress("nobody@here.com");
		String expectedBody = "Hi. This is a test email that somehow escaped to an actual address. Sorry about that.";
		
		System.setProperty("mail.smtp.port", Integer.toString(MOCK_SMTP_PORT));
		System.setProperty("mail.smtp.host", "localhost");
        EmailNotifier emailService = new EmailNotifier();
		
		try {
			emailService.sendEmail(Arrays.asList(mailtoUrl), null, expectedBody);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e);
		}

		server.stop();

		assertThat(server.getReceivedEmailSize(), is(1));
		Iterator emailIter = server.getReceivedEmail();
		SmtpMessage email = (SmtpMessage) emailIter.next();
		assertThat(email.getHeaderValue(EmailHeaders.SUBJECT_HEADER), nullValue());
		assertThat(email.getBody(), is(expectedBody));
	}

	@SuppressWarnings("unchecked")
    @Test(timeout=10000)
	public void shouldBeAbleToSendEmailForNotification() throws MalformedURLException, URISyntaxException, UnsupportedEncodingException, AddressException {
		final int MOCK_SMTP_PORT = 2525;
		SimpleSmtpServer server = SimpleSmtpServer.start(MOCK_SMTP_PORT );
		
		MutableNotification mockNotification = new MutableNotification();
		final String expectedSubject = URLEncoder.encode("email unit test", "UTF-8");
		mockNotification.setDestination(new URI("mailto:nobody@here.com?Subject=" + expectedSubject));
		final String expectedBody = "Hi. This is a test email that somehow escaped to an actual address. Sorry about that.";
		mockNotification.setMessage(expectedBody);
		
		System.setProperty("mail.smtp.port", Integer.toString(MOCK_SMTP_PORT));
		System.setProperty("mail.smtp.host", "localhost");
        EmailNotifier emailService = new EmailNotifier();
		
		try {
			//emailService.sendEmail(Arrays.asList(mailtoUrl), null, expectedBody);
			emailService.notify(mockNotification);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e);
		}

		server.stop();

		assertThat(server.getReceivedEmailSize(), is(1));
		Iterator emailIter = server.getReceivedEmail();
		SmtpMessage email = (SmtpMessage) emailIter.next();
		assertThat(email.getHeaderValue(EmailHeaders.SUBJECT_HEADER), is(expectedSubject));
		assertThat(email.getBody(), is(expectedBody));
	}

}
