package org.gatherdata.alert.notify.mail.internal;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.gatherdata.alert.core.model.MutableSentNotice;
import org.gatherdata.alert.core.model.Notification;
import org.gatherdata.alert.core.spi.Notifier;

public class EmailNotifier implements Notifier {
	
	private static Log log = LogFactory.getLog(EmailNotifier.class);

    public static final Collection<String> SCHEME_TYPES = Arrays.asList("mailto");
    
	private String smtpHostName = "localhost";
	private int smtpPort = 25;
	private InternetAddress senderAddress;

	public EmailNotifier() {
		try {
	        senderAddress = new InternetAddress("no-reply@nowhere.org");
        } catch (AddressException e) {
        	// should never happen!
        	log.error("EmailNotifier has a bad default sender address. Contact the developers!", e);
        }
	}
	
	public boolean canSendTo(URI notificationAddress) {
		return SCHEME_TYPES.contains(notificationAddress.getScheme());
	}

	public Iterable<String> getSchemeTypes() {
		return SCHEME_TYPES;
	}
	
	public void notify(Notification notice) {
		MutableSentNotice sentNotice = null;
		MailtoUrl mailtoUrl;
		try {
			mailtoUrl = new MailtoUrl(notice.getDestination().toURL());

			sendEmail(mailtoUrl.getMailboxAddresses(), mailtoUrl.getHeaderMap(), notice.getMessage());
			
		} catch (MalformedURLException e) {
			log.error("can't send notice \"" + notice + "\" with bad address.");
		}
	    
	}

	public void sendEmail(Iterable<InternetAddress> toAddresses, Map<String, String> headers, String body) {		
		SimpleEmail email = new SimpleEmail();
		email.setHostName(smtpHostName);
		email.setSmtpPort(smtpPort);
		
		try {
			for (InternetAddress addy : toAddresses) {
				email.addTo(addy.toString());
			}
			email.setFrom(senderAddress.toString());
			
			String subject = "";
			if (headers != null) {
				subject = headers.containsKey(EmailHeaders.SUBJECT_HEADER) ? headers.get(EmailHeaders.SUBJECT_HEADER) : "";
			}
			email.setSubject(subject);
			if (body != null) email.setMsg(body);
			email.send();
		} catch (EmailException e) {
			log.error("failed to send event notification", e);
		}
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

}
