package org.gatherdata.alert.dao.pojo.internal;

import java.net.URI;
import java.util.ArrayList;
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

public class ExampleAlertServiceDao implements AlertServiceDao {
    
    private static Log log = LogFactory.getLog(ExampleAlertServiceDao.class);

    Map<URI, ActionPlan> uriToActionPlanMap = new HashMap<URI, ActionPlan>();
    Map<String, List<RuleSet>> contextToRuleSetMap = new HashMap<String, List<RuleSet>>();

    Map<URI, List<PlannedNotification>> eventToNotificationMap = new HashMap<URI, List<PlannedNotification>>();

    public ExampleAlertServiceDao() {
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

    public boolean exists(URI uidOfActionPlan) {
        return uriToActionPlanMap.containsKey(uidOfActionPlan);
    }

    public ActionPlan get(URI arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public Iterable<ActionPlan> getAll() {
        return uriToActionPlanMap.values();
    }

    public void remove(URI arg0) {
        // TODO Auto-generated method stub

    }

    public ActionPlan save(ActionPlan planToSave) {
        uriToActionPlanMap.put(planToSave.getUid(), planToSave);
        save(planToSave.getRuleSet());
        for(PlannedNotification notification : planToSave.getNotifications()) {
            save(planToSave.getEventType(), notification);
        }
        return planToSave;
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
        // TODO Auto-generated method stub
        return null;
    }

}
