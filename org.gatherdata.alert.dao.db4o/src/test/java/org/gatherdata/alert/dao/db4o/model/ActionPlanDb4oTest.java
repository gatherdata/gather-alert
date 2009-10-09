package org.gatherdata.alert.dao.db4o.model;

import java.util.ArrayList;
import java.util.List;

import org.gatherdata.alert.builder.ActionPlanBuilder;
import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.impl.ActionPlanSupport;
import org.gatherdata.alert.core.model.impl.MutableActionPlan;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import static org.gatherdata.alert.builder.EventTypeBuilder.event;
import static org.gatherdata.alert.builder.LanguageScriptBuilder.expressedIn;
import static org.gatherdata.alert.builder.PlannedNotificationBuilder.address;
import static org.gatherdata.alert.builder.RuleSetBuilder.rules;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.gatherdata.commons.junit.ContainsAll.containsAll;

public class ActionPlanDb4oTest {

	private static int mock = 0;
	
    private ActionPlan createMock() {
    	mock++;
    	return ActionPlanBuilder.plan()
		.lookingFor(
            event("barWithinFoo" + mock).describedAs("any occurrence of 'bar' within 'foo'" + mock))
            .applyingRules(
            		rules("text/xml")
            			.rule(expressedIn("js").script("/bar/.test(body)"))
            )
            .notifying(
            		address("mailto:sysadmin@kollegger.name").message(expressedIn("vm").script("test gather-alert message")),
            		address("mailto:sysadmin@kollegger.name").message(expressedIn("vm").script("test gather-alert message"))
            )
            .build();	
    }
    
    public void shouldConsiderAllNonSavedObjectInstanceAsUnique() {
        final int EXPECTED_ENTITIES = 100;
        for (int i=0; i<100; i++) {
            createMock();
        }
    }

    @Test
    public void shouldEqualEmptyOriginal() {
        ActionPlan originalEntity = new MutableActionPlan();
        
        ActionPlan derivedEntity = new ActionPlanDb4o().copy(originalEntity);
        
        assertThat(derivedEntity, is(originalEntity));
    }
    
    @Test
    public void shouldEqualFullyQualifiedOriginal() {
    	ActionPlan originalEntity = createMock();
    	
        ActionPlan derivedEntity = new ActionPlanDb4o().copy(originalEntity);
        
        assertThat(derivedEntity, is(originalEntity));
    }
    
    @Test
    public void shouldDeeplyEqualFullyQualifiedOriginal() {
        ActionPlan originalEntity = createMock();
        
        ActionPlan derivedEntity = new ActionPlanDb4o().copy(originalEntity);
        
        assertTrue(org.gatherdata.alert.core.model.impl.ActionPlanSupport.deepEquals(derivedEntity, originalEntity));
    }

	@Test
    public void shouldMatchInContainers() {
    	List<ActionPlan> originals = new ArrayList<ActionPlan>();
    	List<ActionPlan> deriveds = new ArrayList<ActionPlan>();
    	for (int i=0; i<10; i++) {
    		ActionPlan original = createMock();
    		originals.add(original);
    		deriveds.add(new ActionPlanDb4o().copy(original));
    	}
    	
    	assertThat(deriveds, containsAll(originals));
    }
}