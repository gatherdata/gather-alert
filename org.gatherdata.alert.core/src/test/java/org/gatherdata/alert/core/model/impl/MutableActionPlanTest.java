/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.model.mock.MockActionPlanFactory;
import org.gatherdata.alert.core.model.mock.MockRuleSetFactory;
import org.gatherdata.commons.model.impl.DescribedEntitySupport;
import org.junit.Test;

public class MutableActionPlanTest {

    @Test
    public void shouldUpdateFromPartialPlanWithRuleSetUpdate() {
        MutableActionPlan expectedPlan = MockActionPlanFactory.create();
        
        // planToChange start as a copy with one changed field
        MutableActionPlan planToUpdate = new MutableActionPlan();
        planToUpdate.copy(expectedPlan);
        assertTrue(ActionPlanSupport.deepEquals(expectedPlan, planToUpdate));
        
        // change one field
        MutableRuleSet changedRuleset = new MutableRuleSet();
        changedRuleset.copy(planToUpdate.getRuleSet());
        LanguageScript originalPredicate = planToUpdate.getRuleSet().getPredicates().iterator().next();
        MutableLanguageScript changedField = new MutableLanguageScript();
        changedField.copy(originalPredicate);
        changedField.setLanguage("changed");
        changedRuleset.clearPredicates();
        changedRuleset.add(changedField);
        planToUpdate.setRuleSet(changedRuleset);
        
        // should now be un-equal
        assertFalse(ActionPlanSupport.deepEquals(expectedPlan, planToUpdate));
   
        // partial has only the changed field
        MutableActionPlan partialPlan = new MutableActionPlan();
        MutableRuleSet originalRuleSet = new MutableRuleSet();
        originalRuleSet.copy(expectedPlan.getRuleSet());
        partialPlan.setRuleSet(originalRuleSet);
        
        // update the altered plan, which should bring it back to matching the original
        planToUpdate.update(partialPlan);
        assertTrue(ActionPlanSupport.deepEquals(planToUpdate, expectedPlan));
    }

    @Test
    public void shouldUpdateFromPartialPlanWithNotificationUpdate() {
        MutableActionPlan expectedPlan = MockActionPlanFactory.create();
        
        // planToChange start as a copy with one changed field
        MutableActionPlan planToUpdate = new MutableActionPlan();
        planToUpdate.copy(expectedPlan);
        assertTrue(ActionPlanSupport.deepEquals(expectedPlan, planToUpdate));
        
        // change one field
        MutablePlannedNotification changedNotice = new MutablePlannedNotification();
        PlannedNotification originalNotification = planToUpdate.getNotifications().iterator().next();
        changedNotice.setUid(originalNotification.getUid());
        planToUpdate.clearNotifications();
        planToUpdate.add(changedNotice);
        
        // should now be un-equal
        assertFalse(ActionPlanSupport.deepEquals(expectedPlan, planToUpdate));
   
        // partial has only the changed field
        MutableActionPlan partialPlan = new MutableActionPlan();
        MutablePlannedNotification copiedOriginal = new MutablePlannedNotification();
        copiedOriginal.copy(originalNotification);
        partialPlan.add(copiedOriginal);
        
        // update the altered plan, which should bring it back to matching the original
        planToUpdate.update(partialPlan);
        assertTrue(ActionPlanSupport.deepEquals(planToUpdate, expectedPlan));
    }
}
