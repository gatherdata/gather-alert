package org.gatherdata.alert.notify.mail.osgi;

import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.TypeLiterals.export;
import static org.ops4j.peaberry.util.TypeLiterals.iterable;

import java.util.HashMap;
import java.util.Map;


import org.gatherdata.alert.core.spi.Notifier;
import org.gatherdata.alert.notify.mail.internal.EmailNotifier;
import org.ops4j.peaberry.Export;
import org.osgi.framework.Constants;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.Singleton;

public class GuiceBindingModule extends AbstractModule {


    @Override
    protected void configure() {
        Map<String, String> attrs = new HashMap<String, String>();
        attrs.put(Constants.SERVICE_PID, EmailNotifier.SERVICE_PID);
        bind(export(Notifier.class)).toProvider(service(EmailNotifier.class).attributes(attrs).export());
        bind(EmailNotifier.class).in(Singleton.class); // make it a singleton
    }
}
