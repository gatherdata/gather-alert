package org.gatherdata.alert.dao.neo4j.osgi;

import static com.google.inject.Guice.createInjector;
import static org.ops4j.peaberry.Peaberry.osgiModule;

import org.gatherdata.alert.core.spi.AlertServiceDao;
import org.ops4j.peaberry.Export;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.google.inject.Inject;

/**
 * Extension of the default OSGi bundle activator
 */
public final class OSGiActivator implements BundleActivator {
    
    @Inject
    Export<AlertServiceDao> alertServiceDao;
    
    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start(BundleContext bc) throws Exception {
        createInjector(osgiModule(bc), new GuiceBindingModule()).injectMembers(this);
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop(BundleContext bc) throws Exception {

    }
}