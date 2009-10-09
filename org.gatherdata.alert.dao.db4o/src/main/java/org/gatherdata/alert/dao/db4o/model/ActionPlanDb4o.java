package org.gatherdata.alert.dao.db4o.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.model.impl.ActionPlanSupport;
import org.gatherdata.commons.db.db4o.model.DescribedEntityDb4o;
import org.joda.time.DateTime;

public class ActionPlanDb4o extends DescribedEntityDb4o implements ActionPlan {

    protected static final ActionPlanSupport support = new ActionPlanSupport();

    protected DetectableEventTypeDb4o eventType;
    protected List<PlannedNotificationDb4o> notifications = new ArrayList<PlannedNotificationDb4o>();
    protected RuleSetDb4o ruleset;
    
    public DetectableEventType getEventType() {
        return this.eventType;
    }
    
    public void setEventType(DetectableEventTypeDb4o eventType) {
        this.eventType = eventType;
    }

    public Iterable<? extends PlannedNotification> getNotifications() {
        return this.notifications;
    }
    
    public void addAll(Iterable<? extends PlannedNotification> notifications) {
        for (PlannedNotification notification : notifications) {
            this.notifications.add(new PlannedNotificationDb4o().copy(notification));
        }
    }

    public RuleSet getRuleSet() {
        return this.ruleset;
    }
    
    public void setRuleSet(RuleSetDb4o ruleset) {
        this.ruleset = ruleset;
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
    
    @Override
    public String toString() {
        return "ActionPlan [name =" + getName() + "; eventType =" + getEventType() + "]";
    }


    public ActionPlanDb4o copy(ActionPlan template) {
        if ((template != null) && (template != this)) {
            super.copy(template);
            notifications.clear();
            addAll(template.getNotifications());
            RuleSet templateRuleSet = template.getRuleSet();
            if (templateRuleSet != null) {
               setRuleSet(new RuleSetDb4o().copy(templateRuleSet));
            }
            DetectableEventType templateEventType = template.getEventType();
            if (templateEventType != null) {
            	setEventType((DetectableEventTypeDb4o) new DetectableEventTypeDb4o().copy(templateEventType));
            }
        }
        return this;
    }

}
