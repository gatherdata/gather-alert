package org.gatherdata.alert.reaction.email.model;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.verify;

public class GenericEmailReactionTest {

	@Test
	public void shouldSendPlainTextEmail() throws MalformedURLException {
		EmailReactor mockReactor = createMock(EmailReactor.class);
		URL mailtoUrl = new URL("mailto:nobody@nowhere.gov");
		String body = "this is plain text, with no fancy template stuff.";
		mockReactor.sendEmail(mailtoUrl, body);
		
		replay(mockReactor);
		
		GenericEmailReaction emailReaction = new GenericEmailReaction(mockReactor);
		emailReaction.setReactor(mockReactor);
		
	}
}
