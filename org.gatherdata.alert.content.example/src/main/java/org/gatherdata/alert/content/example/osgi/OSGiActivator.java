package org.gatherdata.alert.content.example.osgi;

import static com.google.inject.Guice.createInjector;
import static org.gatherdata.alert.builder.LanguageScriptBuilder.expressedIn;
import static org.gatherdata.alert.builder.PlannedNotificationBuilder.address;
import static org.gatherdata.alert.builder.RuleSetBuilder.rules;
import static org.ops4j.peaberry.Peaberry.osgiModule;

import org.gatherdata.alert.builder.ActionPlanBuilder;
import org.gatherdata.alert.core.spi.AlertService;
import org.ops4j.peaberry.Export;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.google.inject.Inject;

/**
 * Extension of the default OSGi bundle activator
 */
public final class OSGiActivator implements BundleActivator {

    @Inject
    AlertService alertService;

    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start(BundleContext bc) throws Exception {
        createInjector(osgiModule(bc), new GuiceBindingModule()).injectMembers(this);

        alertService.save(ActionPlanBuilder.plan()
                .named("barWithinFoo")
                .describedAs("any occurrence of 'bar' within 'foo'")
                .applyingRules(
                        rules("text/xml")
                            .rule(expressedIn("js").script("/bar/.test(body)"))
                    )
                .notifying(
                        address("mailto:sysadmin@kollegger.name").message(expressedIn("vm").script("test gather-alert message"))
                    )
                .build());

    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop(BundleContext bc) throws Exception {

    }
}
