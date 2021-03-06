/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.dao.pojo.internal;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gatherdata.alert.core.model.ActionPlan;
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

    public ActionPlan get(URI uidOfActionPlan) {
    	return uriToActionPlanMap.get(uidOfActionPlan);
    }

    public Iterable<ActionPlan> getAll() {
        return uriToActionPlanMap.values();
    }
    
    public int getCount() {
        return uriToActionPlanMap.values().size();
    }

    public void remove(URI arg0) {
        // TODO Auto-generated method stub

    }

    public ActionPlan save(ActionPlan planToSave) {
        uriToActionPlanMap.put(planToSave.getUid(), planToSave);
        save(planToSave.getRuleSet());
        return planToSave;
    }


}
