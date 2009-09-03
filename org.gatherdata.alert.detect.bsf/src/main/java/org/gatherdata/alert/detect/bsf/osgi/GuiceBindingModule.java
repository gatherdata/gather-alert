package org.gatherdata.alert.detect.bsf.osgi;

import java.util.Properties;

import org.gatherdata.alert.core.spi.AlertService;
import org.gatherdata.alert.core.spi.EventDetector;
import org.gatherdata.alert.detect.bsf.internal.BsfEventDetector;
import org.osgi.framework.Constants;

import com.google.inject.AbstractModule;
import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.Attributes.properties;
import static org.ops4j.peaberry.util.TypeLiterals.export;
import static org.ops4j.peaberry.util.TypeLiterals.iterable;

public class GuiceBindingModule extends AbstractModule {

    @Override
    protected void configure() {
    	
        bind(AlertService.class).toProvider(service(AlertService.class).single());
        
        Properties ccAttrs = new Properties();
        ccAttrs.put(Constants.SERVICE_RANKING, new Long(0));
        bind(export(EventDetector.class))
            .toProvider(service(new BsfEventDetector())
                .attributes(properties(ccAttrs))
                .export());

    }
    
}
