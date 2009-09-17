package org.gatherdata.alert.dao.db4o.model;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.MutableActionPlan;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class ActionPlanDb4oTest {

    @Test
    public void shouldEqualOriginalWhenDerived() {
        ActionPlan originalEntity = new MutableActionPlan();
        
        ActionPlan derivedEntity = new ActionPlanDb4o().copy(originalEntity);
        
        assertThat(derivedEntity, is(originalEntity));
    }
}