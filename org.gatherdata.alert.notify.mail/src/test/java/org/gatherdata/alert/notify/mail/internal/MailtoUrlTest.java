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

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;

public class MailtoUrlTest {

    @Test
    public void shouldGetEmptyHeadersWhenNoneProvided() throws MalformedURLException {
        MailtoUrl testUrl = new MailtoUrl("mailto:sysadmin@tembopublic.org");
        assertThat(testUrl.getHeaderMap(), notNullValue());
    }
    
    @Test
    public void shouldParseMailToUrlAsUri() throws URISyntaxException, MalformedURLException {
        URI mailto = new URI("mailto:sysadmin@tembopublic.org");
        assertThat(mailto.getScheme(), is("mailto"));
        URL asURL = mailto.toURL();
        assertThat(asURL, notNullValue());
    }
}
