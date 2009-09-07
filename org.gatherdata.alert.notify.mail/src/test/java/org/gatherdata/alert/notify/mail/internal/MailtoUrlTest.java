package org.gatherdata.alert.notify.mail.internal;

import java.net.MalformedURLException;

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
}
