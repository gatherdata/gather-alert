package org.gatherdata.alert.builder;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.impl.MutableActionPlan;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

public class ActionPlanBuilder implements FluentBuilder<ActionPlan> {

    private final MutableActionPlan actionPlan = new MutableActionPlan();
    
    private EventTypeBuilder eventTypeBuilder;
    
    private ActionPlanBuilder() {
        ;
    }
    
    public static ActionPlanBuilder plan() {
        return new ActionPlanBuilder();
    }

    public ActionPlan build() {
        if (actionPlan.getEventType() == null) {
            throw new IllegalStateException("ActionPlan.EventType must be specified in DSL");
        }
        if (actionPlan.getDateCreated() == null) {
            actionPlan.setDateCreated(new DateTime());
        }
        if (actionPlan.getUid() == null) {
            actionPlan.setUid(CbidFactory.createCbid(actionPlan.getEventType().toString() + actionPlan.getDateCreated().toString()));
        }
        return actionPlan;
    }
    
    public ActionPlanBuilder lookingFor(EventTypeBuilder eventTypeBuilder) {
        actionPlan.setEventType(eventTypeBuilder.build());
        return this;
    }
    
    
    public ActionPlanBuilder applyingRules(RuleSetBuilder ruleBuilder) {
        ruleBuilder.setEventType(actionPlan.getEventType());
        actionPlan.setRuleSet(ruleBuilder.build());
        return this;
    }

    public ActionPlanBuilder notifying(PlannedNotificationBuilder... plannedNotifications) {
        for (PlannedNotificationBuilder plannedNotification : plannedNotifications) {
            plannedNotification.setEventType(actionPlan.getEventType());
            actionPlan.add(plannedNotification.build());
        }
        return this;
    }
    
}
