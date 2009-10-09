package org.gatherdata.alert.core.model.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.model.UniqueEntity;
import org.gatherdata.commons.model.impl.MutableEntity;

public class MutableActionPlan extends MutableEntity implements ActionPlan {

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

    public ActionPlan copy(ActionPlan template) {
        if (template != null) {
            super.copy(template);
            DetectableEventType templateEvent = template.getEventType();
            if (templateEvent != null) {
                setEventType(new MutableDetectableEventType().copy(templateEvent));
            } else {
                setEventType(null);
            }
            plannedNotifications.clear();
            Iterable<? extends PlannedNotification> templateNotifications = template.getNotifications();
            if (templateNotifications != null) {
                for (PlannedNotification templateNotification : templateNotifications) {
                    add(new MutablePlannedNotification().copy(templateNotification));
                }
            }
            RuleSet templateRuleSet = template.getRuleSet();
            if (templateRuleSet != null) {
                setRuleSet(new MutableRuleSet().copy(templateRuleSet));
            } else {
                setRuleSet(null);
            }
        }
        return this;
    }

    public ActionPlan update(ActionPlan template) {
        if (template != null) {
            super.update(template);
            DetectableEventType templateEvent = template.getEventType();
            if (templateEvent != null) {
                MutableDetectableEventType updatedEvent = new MutableDetectableEventType();
                updatedEvent.copy(getEventType());
                updatedEvent.update(templateEvent);
                setEventType(updatedEvent);
            }
            for (PlannedNotification templateNotification : template.getNotifications()) {
                if (!plannedNotifications.contains(templateNotification)) {
                    add(templateNotification);
                }
            }
            RuleSet templateRuleSet = template.getRuleSet();
            if (templateEvent != null) {
                MutableRuleSet updatedRuleSet = new MutableRuleSet();
                updatedRuleSet.copy(getRuleSet());
                updatedRuleSet.update(templateRuleSet);
                setRuleSet(updatedRuleSet);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return "ActionPlan [eventType = " + getEventType() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ActionPlan))
            return false;
        ActionPlan rhs = (ActionPlan) obj;
        return support.equals(this, rhs);
    }

    @Override
    public int hashCode() {
        return support.hashCode(this);
    }

    @Override
    public URI selfIdentify() {
        if (eventType != null) eventType.selfIdentify();
        if (ruleset != null) ruleset.selfIdentify();
        for (PlannedNotification notification : plannedNotifications) {
            notification.selfIdentify();
        }
        return super.selfIdentify();
    }
    
    

}
