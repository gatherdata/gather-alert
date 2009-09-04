package org.gatherdata.alert.builder;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.MutableActionPlan;
import org.gatherdata.commons.net.CbidFactory;

public class ActionPlanBuilder {

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
        if (actionPlan.getName() == null) {
            actionPlan.setName("operation detect-o");
        }
        if (actionPlan.getDescription() == null) {
            actionPlan.setDescription("top-secret agenda");
        }
        if (actionPlan.getUid() == null) {
            actionPlan.setUid(CbidFactory.createCbid(actionPlan.getEventType().toString() + actionPlan.getName()
                    + actionPlan.getDescription()));
        }
        return actionPlan;
    }

    public ActionPlanBuilder named(String nameOfPlan) {
        actionPlan.setName(nameOfPlan);
        return this;
    }

    public ActionPlanBuilder describedAs(String description) {
        actionPlan.setDescription(description);
        return this;
    }
    
    public ActionPlanBuilder lookingFor(EventTypeBuilder eventTypeBuilder) {
        actionPlan.setEventType(eventTypeBuilder.build());
        return this;
    }
    
    
    public ActionPlanBuilder applyingRules(RuleSetBuilder ruleBuilder) {
        actionPlan.setRuleSet(ruleBuilder.build(actionPlan.getEventType()));
        return this;
    }
    
}
