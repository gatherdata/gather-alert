package org.gatherdata.alert.core.model.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.model.impl.MutableDescribedEntity;

public class MutableActionPlan extends MutableDescribedEntity implements ActionPlan {

    /**
     * Auto-generated. 
     */
    private static final long serialVersionUID = 1052452988107447955L;
    
    protected static final ActionPlanSupport support = new ActionPlanSupport();
    
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
        return "ActionPlan [eventType = " + getEventType() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ActionPlan)) return false;
        ActionPlan rhs = (ActionPlan)obj;
        return support.equals(this, rhs);
    }

    @Override
    public int hashCode() {
        return support.hashCode(this);
    }

    
    
}
