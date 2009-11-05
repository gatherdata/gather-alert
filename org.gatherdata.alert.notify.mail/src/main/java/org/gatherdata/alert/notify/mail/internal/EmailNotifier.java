/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
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
import org.gatherdata.alert.core.model.Notification;
import org.gatherdata.alert.core.model.impl.MutableSentNotice;
import org.gatherdata.alert.core.spi.Notifier;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

public class EmailNotifier implements Notifier, ManagedService {
	 
	private static Log log = LogFactory.getLog(EmailNotifier.class);

    public static final Collection<String> SCHEME_TYPES = Arrays.asList("mailto");

	public static final String SERVICE_PID = "org.gatherdata.alert.notify.mail";

	private static final String DEFAULT_SMTP_PORT = "25";

	private static final String DEFAULT_SMTP_HOST = "localhost";

    private static final String DEFAULT_MAIL_FROM = "no-reply@nowhere.org";

	private static final String NO_AUTHENTICATION = "no-authentication";

    public static final String SMTP_PORT_PROPERTY = "mail.smtp.port";

    public static final String SMTP_HOST_PROPERTY = "mail.smtp.host";

    public static final String MAIL_USER_PROPERTY = "mail.user";

    public static final String MAIL_PASSWORD_PROPERTY = "mail.password";
    
    public static final String MAIL_FROM_PROPERTY = "mail.from";
    
	private InternetAddress senderAddress;

    private int smtpPort;

    private String smtpHost;

    private String mailUser;

    private String mailPassword;

	public EmailNotifier() {
	    try {
            updated(null);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
	}
	
	public boolean canSendTo(URI notificationAddress) {
	    log.debug("canSendTo(" + notificationAddress + ")");
	    log.debug("\t scheme:" + notificationAddress.getScheme());
        log.debug("\t scheme specific:" + notificationAddress.getSchemeSpecificPart());
        log.debug("\t fragment:" + notificationAddress.getFragment());
        log.debug("\t host:" + notificationAddress.getHost());
        log.debug("\t path:" + notificationAddress.getPath());
        try {
            log.debug("\t as URL:" + notificationAddress.toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
		
		email.setHostName(smtpHost);
		email.setSmtpPort(smtpPort);
		
		
		if (!NO_AUTHENTICATION.equals(mailUser) && !"".equals(mailUser)) {
			if (!NO_AUTHENTICATION.equals(mailPassword)) {
				email.setAuthentication(mailUser, mailPassword);
			}
		}
		
	}

    public void updated(Dictionary properties) throws ConfigurationException {
        smtpPort = Integer.parseInt(System.getProperty(SMTP_PORT_PROPERTY, DEFAULT_SMTP_PORT));
        smtpHost = System.getProperty(SMTP_HOST_PROPERTY, DEFAULT_SMTP_HOST);
        mailUser = System.getProperty(MAIL_USER_PROPERTY, NO_AUTHENTICATION);
        mailPassword = System.getProperty(MAIL_PASSWORD_PROPERTY, NO_AUTHENTICATION);
        String mailFrom = System.getProperty(MAIL_FROM_PROPERTY, DEFAULT_MAIL_FROM);

        if (properties != null) {
            String updateSmtpPort = (String)properties.get(SMTP_PORT_PROPERTY);
            if (updateSmtpPort != null) smtpPort = Integer.parseInt(updateSmtpPort);
            
            String updateSmtpHost = (String)properties.get(SMTP_HOST_PROPERTY);
            if (updateSmtpHost != null) smtpHost = updateSmtpHost;
            
            String updateMailUser = (String)properties.get(MAIL_USER_PROPERTY);
            if (updateMailUser != null) mailUser = updateMailUser;
            
            String updateMailPassword = (String)properties.get(MAIL_PASSWORD_PROPERTY);
            if (updateMailPassword != null) mailPassword = updateMailPassword;
            
            String updateMailFrom = (String)properties.get(MAIL_FROM_PROPERTY);
            if (updateMailFrom != null) mailFrom = updateMailFrom;
        }
        
        try {
            senderAddress = new InternetAddress(mailFrom);
        } catch (AddressException e) {
            log.error("EmailNotifier has a bad default sender address. Contact the developers!", e);
        }
        
    }

}
