package org.gatherdata.alert.reaction.email.internal;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.gatherdata.alert.reaction.email.model.EmailHeaders;
import org.gatherdata.alert.reaction.email.model.EmailReaction;
import org.gatherdata.alert.reaction.email.model.GenericEmailReaction;
import org.gatherdata.alert.reaction.email.spi.EmailReactionService;
import org.junit.Test;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;

public class EmailReactionServiceImplTest {

	@SuppressWarnings("unchecked")
    @Test
	public void shouldBeAbleToSendPlainEmail() throws MalformedURLException, URISyntaxException, UnsupportedEncodingException {
		SimpleSmtpServer server = SimpleSmtpServer.start(2525);

		String expectedSubject = URLEncoder.encode("email unit test", "UTF-8");
		URL mailtoUrl = new URI("mailto:nobody@here.com?Subject=" + expectedSubject).toURL();
		String expectedBody = "Hi. This is a test email that somehow escaped to an actual address. Sorry about that.";
		
		EmailReactionService emailService = new EmailReactionServiceImpl();
		emailService.setSmtpPort(2525);
		
		try {
			emailService.sendEmail(mailtoUrl, expectedBody);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e);
		}

		server.stop();

		assertTrue(server.getReceivedEmailSize() == 1);
		Iterator emailIter = server.getReceivedEmail();
		SmtpMessage email = (SmtpMessage) emailIter.next();
		assertTrue(expectedSubject.equals(email.getHeaderValue(EmailHeaders.SUBJECT_HEADER)));
		assertTrue(expectedBody.equals(email.getBody()));
	}
	
	@Test
	public void shouldSendEmailBasedOnEmailReaction() throws UnsupportedEncodingException, MalformedURLException, URISyntaxException {
		SimpleSmtpServer server = SimpleSmtpServer.start(2525);

		String expectedSubject = URLEncoder.encode("email unit test", "UTF-8");
		
		URL mailtoUrl = new URI("mailto:nobody@here.com?subject=" + expectedSubject).toURL();
		String expectedBody = "Hi. This is a test email that somehow escaped to an actual address. Sorry about that.";
		
		EmailReactionService emailService = new EmailReactionServiceImpl();
		emailService.setSmtpPort(2525);

		GenericEmailReaction emailReaction = new GenericEmailReaction(emailService);
		emailReaction.setMailtoURL(mailtoUrl);
		emailReaction.setBodyTemplate(expectedBody);
		
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("subject", expectedSubject);
		
		try {
			emailService.sendEmail(emailReaction);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e);
		}

		server.stop();

		assertTrue(server.getReceivedEmailSize() == 1);
		Iterator emailIter = server.getReceivedEmail();
		SmtpMessage email = (SmtpMessage) emailIter.next();
		assertTrue(email.getHeaderValue("Subject").equals(expectedSubject));
		assertTrue(email.getBody().equals(expectedBody));

	}
}
