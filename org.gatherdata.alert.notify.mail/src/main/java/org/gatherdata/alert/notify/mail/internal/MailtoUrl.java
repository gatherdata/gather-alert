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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.WordUtils;

public class MailtoUrl {

    private URL internalUrl;
    private HashMap<String, String> headerMap;

    public MailtoUrl(String mailtoUrl) throws MalformedURLException {
        this(new URL(mailtoUrl));
    }

    public MailtoUrl(URL toUrl) {
        this.internalUrl = toUrl;

        // ABKTODO: validate with a regex
    }

    public Map<String, String> getHeaderMap() {
        if (headerMap == null) {
            headerMap = new HashMap<String, String>();
            String mailQuery = internalUrl.getQuery();
            if (mailQuery != null) {
                StringTokenizer headerTokens = new StringTokenizer(mailQuery, "&");
                while (headerTokens.hasMoreTokens()) {
                    String header = headerTokens.nextToken();
                    String[] headerAndValue = header.split("=");
                    headerMap.put(WordUtils.capitalize(headerAndValue[0].toLowerCase(), new char[] { '-' }),
                            headerAndValue[1]);
                }
            }
        }
        return headerMap;
    }

    public String getMailbox() {
        return internalUrl.getPath();
    }

    /**
     * Gets the internet email addresses contained within the mailbox section.
     * 
     * @return
     */
    public List<InternetAddress> getMailboxAddresses() {
        List<InternetAddress> addies = new ArrayList<InternetAddress>();

        String mailbox = getMailbox();
        StringTokenizer mailboxTokens = new StringTokenizer(mailbox, ",");
        while (mailboxTokens.hasMoreTokens()) {
            try {
                addies.add(new InternetAddress(mailboxTokens.nextToken().trim()));
            } catch (AddressException e) {
                System.err.println("failed to parse all internet addresses from mailbox: " + mailbox);
                e.printStackTrace();
            }
        }
        return addies;
    }

    public String getSubject() {
        return getHeaderMap().get(EmailHeaders.SUBJECT_HEADER);
    }

    public String getScheme() {
        try {
            return internalUrl.toURI().getScheme();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "";
    }

}
