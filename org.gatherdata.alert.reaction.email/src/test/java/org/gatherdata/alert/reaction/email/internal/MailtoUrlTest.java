package org.gatherdata.alert.reaction.email.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.gatherdata.alert.reaction.email.model.MailtoUrl;
import org.junit.Test;

public class MailtoUrlTest {

	@Test
	public void shouldParsePlainEmailAddress() throws MalformedURLException {
		String expectedMailbox = "nobody@here.com";
		URL plainUrl = new URL("mailto:" + expectedMailbox);
		MailtoUrl mailtoUrl = new MailtoUrl(plainUrl);
		
		assertEquals(expectedMailbox, mailtoUrl.getMailbox());
	}
	
	@Test
	public void shouldProvideListOfAddresses() throws MalformedURLException {
		String expectedMailbox = "nobody@here.com, someone@there.org";
		URL plainUrl = new URL("mailto:" + expectedMailbox);
		MailtoUrl mailtoUrl = new MailtoUrl(plainUrl);
		
		List<InternetAddress> addies = mailtoUrl.getMailboxAddresses();
		assertNotNull(addies);
		assertEquals(2, addies.size());
		
	}
}
