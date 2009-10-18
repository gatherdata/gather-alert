package org.gatherdata.alert.core.model.impl;

import org.gatherdata.alert.core.model.ActionPlan;

public class ActionPlanFormatter {
    public static String toString(ActionPlan actionPlan) {
        return "ActionPlan [" + actionPlan.getName() + " : " + actionPlan.getDescription()  + "]";
    }
}
