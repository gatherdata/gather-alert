package org.gatherdata.alert.core.internal;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.spi.AlertServiceDao;
import org.gatherdata.alert.core.spi.AlertService;

import com.google.inject.Inject;

/**
 * Concrete implementation of the AlertService.
 * 
 */
public class AlertServiceImpl implements AlertService {
	Log log = LogFactory.getLog(AlertService.class);
	
	@Inject
    protected AlertServiceDao dao;
	
    public boolean exists(URI uidOfActionPlan) {
        return dao.exists(uidOfActionPlan);
    }

    public ActionPlan get(URI uidOfActionPlan) {
        dao.beginTransaction();
        ActionPlan foundPlan = dao.get(uidOfActionPlan);
        dao.endTransaction();
        return foundPlan;
    }

    public Iterable<ActionPlan> getAll() {
        return dao.getAll();
    }

    public void remove(URI uidOfActionPlan) {
        // TODO Auto-generated method stub
        
    }

    public ActionPlan save(ActionPlan planToSave) {
        return dao.save(planToSave);
    }

    public Iterable<RuleSet> getActiveRulesetsFor(String context) {
        return dao.getActiveRulesetsFor(context);
    }

    public Iterable<PlannedNotification> getPlannedNotificationsFor(DetectableEventType eventType) {
        return dao.getPlannedNotificationsFor(eventType);
    }
	


}
