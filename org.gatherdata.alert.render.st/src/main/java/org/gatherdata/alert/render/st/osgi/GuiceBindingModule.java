package org.gatherdata.alert.render.st.osgi;

import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.Attributes.properties;
import static org.ops4j.peaberry.util.TypeLiterals.export;
import static org.ops4j.peaberry.util.TypeLiterals.iterable;

import org.gatherdata.alert.core.spi.TemplateRenderer;
import org.gatherdata.alert.render.st.internal.StringTemplateRenderer;
import org.ops4j.peaberry.Export;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;

public class GuiceBindingModule extends AbstractModule {

    @Inject
    Export<TemplateRenderer> stringTemplateRenderer;

    @Override
    protected void configure() {
        bind(export(TemplateRenderer.class)).toProvider(service(StringTemplateRenderer.class).export());
        bind(StringTemplateRenderer.class).in(Singleton.class); // make it a singleton
    }
}