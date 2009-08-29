package org.gatherdata.alert.reaction.email.osgi;

import java.util.Dictionary;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.gatherdata.alert.reaction.email.internal.EmailReactionServiceImpl;
import org.gatherdata.alert.reaction.email.spi.EmailReactionService;

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
        System.out.println( "STARTING org.gatherdata.alert.reaction.email" );

        Dictionary props = new Properties();
        // add specific service properties here...

        System.out.println( "REGISTER org.gatherdata.alert.reaction.email.ExampleService" );

        // Register our example service implementation in the OSGi service registry
        bc.registerService( EmailReactionService.class.getName(), new EmailReactionServiceImpl(), props );
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc )
        throws Exception
    {
        System.out.println( "STOPPING org.gatherdata.alert.reaction.email" );

        // no need to unregister our service - the OSGi framework handles it for us
    }
}

