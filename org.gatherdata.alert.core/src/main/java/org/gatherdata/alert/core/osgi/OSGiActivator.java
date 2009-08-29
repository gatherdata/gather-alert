package org.gatherdata.alert.core.osgi;

import static com.google.inject.Guice.createInjector;
import static org.ops4j.peaberry.Peaberry.osgiModule;

import org.ops4j.peaberry.Export;
import java.util.Dictionary;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


/**
 * Extension of the default OSGi bundle activator
 */
public final class OSGiActivator
    implements BundleActivator
{
    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start( BundleContext bc )
        throws Exception
    {
    	createInjector(osgiModule(bc), new GuiceBindingModule()).injectMembers(this);
    	
    	System.out.println("STARTED Gather Alert Core");
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc )
        throws Exception
    {
        System.out.println( "STOPPING Gather Alert Core" );

        // no need to unregister our service - the OSGi framework handles it for us
    }
}

