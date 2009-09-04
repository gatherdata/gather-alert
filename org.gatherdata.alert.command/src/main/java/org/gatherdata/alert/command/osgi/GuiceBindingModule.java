package org.gatherdata.alert.command.osgi;

import java.util.Properties;

import org.gatherdata.alert.command.internal.AlertCommandImpl;
import org.gatherdata.alert.core.spi.AlertService;
import org.gatherdata.alert.core.spi.EventDetector;
import org.gatherdata.alert.core.spi.TemplateRenderer;
import org.osgi.framework.Constants;

import com.google.inject.AbstractModule;
import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.Attributes.properties;
import static org.ops4j.peaberry.util.TypeLiterals.export;
import static org.ops4j.peaberry.util.TypeLiterals.iterable;

public class GuiceBindingModule extends AbstractModule {

    @Override
    protected void configure() {
        // import all ArchiverService
        bind(AlertService.class).toProvider(service(AlertService.class).single());
        bind(EventDetector.class).toProvider(service(EventDetector.class).single());
        bind(iterable(TemplateRenderer.class)).toProvider(service(TemplateRenderer.class).multiple());
        
        // export the CamelCommandImpl
        Properties ccAttrs = new Properties();
        ccAttrs.put(Constants.SERVICE_RANKING, new Long(0));
        bind(export(org.apache.felix.shell.Command.class))
            .toProvider(service(new AlertCommandImpl())
                .attributes(properties(ccAttrs))
                .export());

    }
    
}
