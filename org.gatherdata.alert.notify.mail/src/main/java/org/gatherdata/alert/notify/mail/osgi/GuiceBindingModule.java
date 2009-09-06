package org.gatherdata.alert.notify.mail.osgi;

import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.TypeLiterals.export;
import static org.ops4j.peaberry.util.TypeLiterals.iterable;


import org.gatherdata.alert.core.spi.Notifier;
import org.gatherdata.alert.notify.mail.internal.EmailNotifier;
import org.ops4j.peaberry.Export;
import org.osgi.framework.Constants;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;

public class GuiceBindingModule extends AbstractModule {

    @Inject
    Export<Notifier> emailNotifier;

    @Override
    protected void configure() {
        bind(export(Notifier.class)).toProvider(service(EmailNotifier.class).export());
        bind(EmailNotifier.class).in(Singleton.class); // make it a singleton
    }
}
