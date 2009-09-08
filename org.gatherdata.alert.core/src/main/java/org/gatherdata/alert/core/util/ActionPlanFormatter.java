package org.gatherdata.alert.core.util;

import org.gatherdata.alert.core.model.ActionPlan;

public class ActionPlanFormatter {
    public static String toString(ActionPlan actionPlan) {
        return "ActionPlan [" + EventTypeFormatter.toString(actionPlan.getEventType()) + "]";
    }
}
