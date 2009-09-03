package org.gatherdata.alert.reaction.email.internal;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.collections.Predicate;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.SimpleEmail;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.reaction.email.model.EmailHeaders;
import org.gatherdata.alert.reaction.email.model.EmailReaction;
import org.gatherdata.alert.reaction.email.model.GenericEmailReaction;
import org.gatherdata.alert.reaction.email.model.MailtoUrl;
import org.gatherdata.alert.reaction.email.spi.EmailReactionService;

/**
 * Internal implementation of EmailReactionService
 */
public final class EmailReactionServiceImpl implements EmailReactionService {

	private String smtpHostName = "localhost";
	private int smtpPort = 25;
	private InternetAddress senderAddress;
	
	public EmailReactionServiceImpl() {
		try {
	        senderAddress = new InternetAddress("no-reply@nowhere.org");
        } catch (AddressException e) {
        	// should never happen!
        	System.err.println("EmailReactionServiceImpl() has a bad default sender address. Contact the developers!");
	        e.printStackTrace();
        }
	}
	
	public PlannedNotification get(URI id) {
		return null;
	}

	public List<PlannedNotification> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSmtpHostName(String hostName) {
		this.smtpHostName = hostName;
	}

	public void setSmtpPort(int portNumber) {
		this.smtpPort = portNumber;
	}

	public void sendEmail(URL toUrl, String body) {
		MailtoUrl mailtoUrl = new MailtoUrl(toUrl);
		sendEmail(mailtoUrl.getMailboxAddresses(), mailtoUrl.getHeaderMap(), body);
	}

	public void sendEmail(EmailReaction emailReaction) {
		sendEmail(emailReaction, null);
	}
	
	public void sendEmail(EmailReaction emailReaction, Map<String, Object> bodyVariables) {
		MailtoUrl mailtoUrl = new MailtoUrl(emailReaction.getMailtoURL()); // parse the url as a mailto

	    sendEmail(mailtoUrl.getMailboxAddresses(), mailtoUrl.getHeaderMap(), emailReaction.getBodyTemplate());
    }
	
	public void sendEmail(List<InternetAddress> toAddresses, Map<String, String> headers, String body) {		
		/*
		SimpleEmail email = new SimpleEmail();
		email.setHostName(smtpHostName);
		email.setSmtpPort(smtpPort);
		
		try {
			for (InternetAddress addy : toAddresses) {
				email.addTo(addy.toString());
			}
			email.setFrom(senderAddress.toString());
			String subject = headers.get(EmailHeaders.SUBJECT_HEADER);
			if (subject == null) subject = "";
			email.setSubject(headers.get(EmailHeaders.SUBJECT_HEADER));
			if (body != null) email.setMsg(body);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		*/
	}

}
