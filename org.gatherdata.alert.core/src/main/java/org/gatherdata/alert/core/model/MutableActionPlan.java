package org.gatherdata.alert.core.model;

import java.net.URI;

import org.gatherdata.commons.model.MutableDescribedEntity;

public class MutableActionPlan extends MutableDescribedEntity implements ActionPlan {

    /**
     * Auto-generated. 
     */
    private static final long serialVersionUID = 1052452988107447955L;
    
    private DetectableEventType eventType;
    private PlannedNotification plannedNotification;
    private RuleSet ruleset;

    public DetectableEventType getEventType() {
        return this.eventType;
    }
    
    public void setEventType(DetectableEventType eventType) {
        this.eventType = eventType;
    }

    public PlannedNotification getNotification() {
        return this.plannedNotification;
    }
    
    public void setNotification(PlannedNotification plannedNotification) {
        this.plannedNotification = plannedNotification;
    }

    public RuleSet getRuleSet() {
        return this.ruleset;
    }
    
    public void setRuleSet(RuleSet ruleset) {
        this.ruleset = ruleset;
    }

    @Override
    public String toString() {
        return "ActionPlan [eventType=" + eventType + ", plannedNotification=" + plannedNotification
                + ", ruleset=" + ruleset + "]";
    }

    
}
