package org.gatherdata.alert.core.spi;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.spi.StorageDao;

public interface AlertServiceDao extends StorageDao<ActionPlan> {

    Iterable<RuleSet> getActiveRulesetsFor(String context);

}