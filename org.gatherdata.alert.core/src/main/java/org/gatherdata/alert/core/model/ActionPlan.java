package org.gatherdata.alert.core.model;

import org.gatherdata.commons.model.DescribedEntity;

public interface ActionPlan extends DescribedEntity {

    public DetectableEventType getEventType();
    
    public RuleSet getRuleSet();
    
    public PlannedNotification getNotification();
    
}
