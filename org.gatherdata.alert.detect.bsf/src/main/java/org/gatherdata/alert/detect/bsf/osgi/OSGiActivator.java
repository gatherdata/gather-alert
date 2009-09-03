package org.gatherdata.alert.detect.bsf.osgi;

import java.util.logging.Logger;

import org.gatherdata.alert.core.spi.EventDetector;
import org.ops4j.peaberry.Export;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import static org.ops4j.peaberry.Peaberry.osgiModule;
import static com.google.inject.Guice.createInjector;

import com.google.inject.Inject;


/**
 * Extension of the default OSGi bundle activator
 */
public final class OSGiActivator
    implements BundleActivator
{
    private static final Logger log = Logger.getLogger(OSGiActivator.class.getName());
    
    @Inject
    Export<EventDetector> bsfEventDetector;
    
    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start( BundleContext bc )
        throws Exception
    {
        createInjector(osgiModule(bc), new GuiceBindingModule()).injectMembers(this);
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc )
        throws Exception
    {
    }
}

