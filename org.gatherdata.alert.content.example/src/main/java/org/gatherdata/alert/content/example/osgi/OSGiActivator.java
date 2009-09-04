package org.gatherdata.alert.content.example.osgi;

import static com.google.inject.Guice.createInjector;
import static org.gatherdata.alert.builder.EventTypeBuilder.event;
import static org.gatherdata.alert.builder.LanguageScriptBuilder.expressedIn;
import static org.gatherdata.alert.builder.RuleSetBuilder.rules;
import static org.ops4j.peaberry.Peaberry.osgiModule;

import org.gatherdata.alert.builder.ActionPlanBuilder;
import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.mock.MockActionPlanFactory;
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

        System.out.println("Adding mock data to: " + alertService);
        for (int i = 0; i < 1; i++) {
            alertService.save(MockActionPlanFactory.create());
        }
        alertService.save(ActionPlanBuilder.plan()
                .named("example1")
                .describedAs("an example test plan")
                .lookingFor(
                        event("barWithinFoo").describedAs("any occurrence of 'bar' within 'foo'"))
                .applyingRules(
                        rules("text/xml")
                            .rule(expressedIn("js").script("/bar/.test(body)"))
                ).build());

    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop(BundleContext bc) throws Exception {

    }
}
