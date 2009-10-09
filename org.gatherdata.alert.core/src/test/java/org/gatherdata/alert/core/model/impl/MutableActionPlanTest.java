package org.gatherdata.alert.core.model.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.mock.MockActionPlanFactory;
import org.gatherdata.alert.core.model.mock.MockDetectableEventFactory;
import org.gatherdata.commons.model.impl.DescribedEntitySupport;
import org.junit.Test;

public class MutableActionPlanTest {

    @Test
    public void shouldUpdateFromPartialPlanWithEventNameUpdate() {
        MutableActionPlan expectedPlan = MockActionPlanFactory.create();
        
        // planToChange start as a copy with one changed field
        MutableActionPlan planToUpdate = new MutableActionPlan();
        planToUpdate.copy(expectedPlan);
        assertTrue(ActionPlanSupport.deepEquals(expectedPlan, planToUpdate));
        
        // change one field
        DetectableEventType changedField = MockDetectableEventFactory.create();
        assertFalse(DescribedEntitySupport.deepEquals(changedField, planToUpdate.getEventType()));
        planToUpdate.setEventType(changedField);
        
        // should now be un-equal
        assertFalse(ActionPlanSupport.deepEquals(expectedPlan, planToUpdate));
   
        // partial has only the changed field
        MutableActionPlan partialPlan = new MutableActionPlan();
        partialPlan.setEventType(expectedPlan.getEventType());
        
        // update the altered plan, which should bring it back to matching the original
        planToUpdate.update(partialPlan);
        assertTrue(ActionPlanSupport.deepEquals(planToUpdate, expectedPlan));
    }
    
}
