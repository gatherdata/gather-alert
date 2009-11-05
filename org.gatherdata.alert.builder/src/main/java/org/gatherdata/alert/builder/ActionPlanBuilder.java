/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.builder;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.impl.MutableActionPlan;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

public class ActionPlanBuilder implements FluentBuilder<ActionPlan> {

    private final MutableActionPlan actionPlan = new MutableActionPlan();
        
    private ActionPlanBuilder() {
        ;
    }
    
    public static ActionPlanBuilder plan() {
        return new ActionPlanBuilder();
    }

    public ActionPlan build() {
        if (actionPlan.getName() == null) {
            actionPlan.setName("pass tests");
        }
        if (actionPlan.getDescription() == null) {
            actionPlan.setDescription("fix the code, and the tests will pass.");
        }
        if (actionPlan.getDateCreated() == null) {
            actionPlan.setDateCreated(new DateTime());
        }
        if (actionPlan.getUid() == null) {
            actionPlan.setUid(CbidFactory.createCbid(actionPlan.getName().toString() + actionPlan.getDateCreated().toString()));
        }
        return actionPlan;
    }
    
    public ActionPlanBuilder named(String nameOfPlan) {
        actionPlan.setName(nameOfPlan);
        return this;
    }
    
    public ActionPlanBuilder describedAs(String descriptionOfPlan) {
        actionPlan.setDescription(descriptionOfPlan);
        return this;
    }
    
    
    public ActionPlanBuilder applyingRules(RuleSetBuilder ruleBuilder) {
        actionPlan.setRuleSet(ruleBuilder.build());
        return this;
    }

    public ActionPlanBuilder notifying(PlannedNotificationBuilder... plannedNotifications) {
        for (PlannedNotificationBuilder plannedNotification : plannedNotifications) {
            actionPlan.add(plannedNotification.build());
        }
        return this;
    }
    
}
