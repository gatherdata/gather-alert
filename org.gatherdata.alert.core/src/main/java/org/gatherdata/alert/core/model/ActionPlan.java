package org.gatherdata.alert.core.model;

import org.gatherdata.commons.model.UniqueEntity;

public interface ActionPlan extends UniqueEntity {

    public DetectableEventType getEventType();
    
    public RuleSet getRuleSet();
    
    public Iterable<? extends PlannedNotification> getNotifications();

}
