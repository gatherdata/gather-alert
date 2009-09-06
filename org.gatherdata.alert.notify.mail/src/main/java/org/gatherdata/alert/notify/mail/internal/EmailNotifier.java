package org.gatherdata.alert.notify.mail.internal;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
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
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

public class EmailNotifier implements Notifier {
	 
	private static Log log = LogFactory.getLog(EmailNotifier.class);

    public static final Collection<String> SCHEME_TYPES = Arrays.asList("mailto");

	public static final String SERVICE_PID = "org.gatherdata.alert.notify.EmailNotifier";

	private static final String DEFAULT_SMTP_PORT = "25";

	private static final String DEFAULT_SMTP_HOST = "localhost";

	private static final String NO_AUTHENTICATION = "no-authentication";
    
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
		
		configure(email);
		
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

	private void configure(SimpleEmail email) {
		int smtpPort = Integer.parseInt(System.getProperty("mail.smtp.port", DEFAULT_SMTP_PORT));
		String smtpHost = System.getProperty("mail.smtp.host", DEFAULT_SMTP_HOST);
		
		email.setHostName(smtpHost);
		email.setSmtpPort(smtpPort);
		
		String mailUser = System.getProperty("mail.user", NO_AUTHENTICATION);
		
		if (!NO_AUTHENTICATION.equals(mailUser)) {
			String mailPassword = System.getProperty("mail.password", NO_AUTHENTICATION);
			if (!NO_AUTHENTICATION.equals(mailPassword)) {
				email.setAuthentication(mailUser, mailPassword);
			}
		}
		
	}

}
