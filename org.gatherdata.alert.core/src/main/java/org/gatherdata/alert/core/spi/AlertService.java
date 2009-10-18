package org.gatherdata.alert.core.spi;


import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.spi.StorageService;

/**
 * A service for managing alert ActionPlans, and the EventTypes,
 * RuleSets and PlannedNotifications which they contain.
 *
 */
public interface AlertService extends StorageService<ActionPlan> {
    
    public Iterable<RuleSet> getActiveRulesetsFor(String context);
    
}
