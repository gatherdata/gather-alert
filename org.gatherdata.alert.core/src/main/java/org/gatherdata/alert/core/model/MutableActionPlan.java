package org.gatherdata.alert.core.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.gatherdata.commons.model.MutableDescribedEntity;

public class MutableActionPlan extends MutableDescribedEntity implements ActionPlan {

    /**
     * Auto-generated. 
     */
    private static final long serialVersionUID = 1052452988107447955L;
    
    private DetectableEventType eventType;
    private final List<PlannedNotification> plannedNotifications = new ArrayList<PlannedNotification>();
    private RuleSet ruleset;

    public DetectableEventType getEventType() {
        return this.eventType;
    }
    
    public void setEventType(DetectableEventType eventType) {
        this.eventType = eventType;
    }

    public Iterable<PlannedNotification> getNotifications() {
        return this.plannedNotifications;
    }
    
    public void add(PlannedNotification plannedNotification) {
        this.plannedNotifications.add(plannedNotification);
    }

    public RuleSet getRuleSet() {
        return this.ruleset;
    }
    
    public void setRuleSet(RuleSet ruleset) {
        this.ruleset = ruleset;
    }

    @Override
    public String toString() {
        return "ActionPlan [eventType=" + eventType + "]";
    }

    
}
