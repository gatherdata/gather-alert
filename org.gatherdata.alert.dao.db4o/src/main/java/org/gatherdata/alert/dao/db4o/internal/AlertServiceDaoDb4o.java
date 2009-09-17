package org.gatherdata.alert.dao.db4o.internal;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.MutableDetectableEventType;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.spi.AlertServiceDao;
import org.gatherdata.alert.dao.db4o.model.ActionPlanDb4o;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.google.inject.Inject;

public class AlertServiceDaoDb4o implements AlertServiceDao {
    
    private static Log log = LogFactory.getLog(AlertServiceDaoDb4o.class);

    @Inject
    ObjectContainer db4o;

    Map<String, List<RuleSet>> contextToRuleSetMap = new HashMap<String, List<RuleSet>>();

    Map<URI, List<PlannedNotification>> eventToNotificationMap = new HashMap<URI, List<PlannedNotification>>();

    public AlertServiceDaoDb4o() {
        ;
    }

    public void save(RuleSet ruleSetToSave) {
        String context = ruleSetToSave.getContext();
        List<RuleSet> rulesetList = contextToRuleSetMap.get(context);
        if (rulesetList == null) {
            rulesetList = new ArrayList<RuleSet>();
            contextToRuleSetMap.put(context, rulesetList);
        }
        rulesetList.add(ruleSetToSave);
    }

    public Iterable<RuleSet> getActiveRulesetsFor(String context) {
        List<RuleSet> possibleRules = contextToRuleSetMap.get(context);
        List<RuleSet> activeRules = new ArrayList<RuleSet>();
        if (possibleRules != null) {
            for (RuleSet possibleRule : possibleRules) {
                if (possibleRule.isActive()) {
                    activeRules.add(possibleRule);
                }
            }
        }
        return activeRules;
    }

    public void beginTransaction() {
        ; // no-op
    }

    public void endTransaction() {
        ; // no-op
    }

    public boolean exists(URI uid) {
        return (get(uid) != null);
    }

    public ActionPlan get(URI uid) {
        ActionPlanDb4o foundEntity = null;
        
        final URI queryUid = uid;
        ObjectSet<ActionPlanDb4o> result = db4o.query(new Predicate<ActionPlanDb4o>() {
            @Override
            public boolean match(ActionPlanDb4o possibleMatch) {
                return possibleMatch.getUid().equals(queryUid);
            }
        });
        if (result.size() > 1) {
            log.warn("more than one ActionPlan found for uid: " + queryUid);
        }
        if (result.hasNext()) {
            foundEntity = result.next();
        }
        
        return foundEntity;
    }

    public Iterable<? extends ActionPlan> getAll() {
        return db4o.query(ActionPlanDb4o.class);
    }

    public void remove(URI uid) {
        db4o.delete(get(uid));
    }

    public ActionPlan save(ActionPlan entityToSave) {
        ActionPlanDb4o entityDto = (ActionPlanDb4o) get(entityToSave.getUid());
        if (entityDto == null) {
            entityDto = new ActionPlanDb4o();
        }
        entityDto.copy(entityToSave);
        db4o.store(entityDto);
        
        return get(entityToSave.getUid());
    }

    public void save(DetectableEventType eventType, PlannedNotification notification) {
        List<PlannedNotification> notificationList = eventToNotificationMap.get(eventType.getUid());
        if (notificationList == null) {
            notificationList = new ArrayList<PlannedNotification>();
            eventToNotificationMap.put(eventType.getUid(), notificationList);
        }
        notificationList.add(notification);
    }

    public Iterable<PlannedNotification> getPlannedNotificationsFor(DetectableEventType eventType) {
    	List<PlannedNotification> notificationList = eventToNotificationMap.get(eventType.getUid());
        if (notificationList == null) {
        	notificationList = Collections.emptyList();
        }
        return notificationList;
    }

}
