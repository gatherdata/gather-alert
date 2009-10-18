package org.gatherdata.alert.core.model;

import org.gatherdata.commons.model.DescribedEntity;

public interface ActionPlan extends DescribedEntity {

    public RuleSet getRuleSet();
    
    public Iterable<? extends PlannedNotification> getNotifications();

}
