/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.model.UniqueEntity;
import org.gatherdata.commons.model.impl.MutableDescribedEntity;
import org.gatherdata.commons.model.impl.MutableEntity;

public class MutableActionPlan extends MutableDescribedEntity implements ActionPlan {

    /**
     * Auto-generated.
     */
    private static final long serialVersionUID = 1052452988107447955L;

    protected static final ActionPlanSupport support = new ActionPlanSupport();

    private final List<PlannedNotification> plannedNotifications = new ArrayList<PlannedNotification>();
    private RuleSet ruleset;

    public Iterable<PlannedNotification> getNotifications() {
        return this.plannedNotifications;
    }

    public void add(MutablePlannedNotification plannedNotification) {
        plannedNotification.setPlan(this);
        this.plannedNotifications.add(plannedNotification);
    }

    public RuleSet getRuleSet() {
        return this.ruleset;
    }

    public void setRuleSet(MutableRuleSet ruleset) {
        ruleset.setPlan(this);
        this.ruleset = ruleset;
    }

    public ActionPlan copy(ActionPlan template) {
        if (template != null) {
            super.copy(template);
            plannedNotifications.clear();
            Iterable<? extends PlannedNotification> templateNotifications = template.getNotifications();
            if (templateNotifications != null) {
                for (PlannedNotification templateNotification : templateNotifications) {
                    MutablePlannedNotification copiedNotification = new MutablePlannedNotification();
                    copiedNotification.copy(templateNotification);
                    add(copiedNotification);
                }
            }
            RuleSet templateRuleSet = template.getRuleSet();
            if (templateRuleSet != null) {
                MutableRuleSet copiedRuleset = new MutableRuleSet();
                copiedRuleset.copy(templateRuleSet);
                setRuleSet(copiedRuleset);
            } else {
                setRuleSet(null);
            }
        }
        return this;
    }

    public ActionPlan update(ActionPlan template) {
        if (template != null) {
            super.update(template);
            for (PlannedNotification templateNotification : template.getNotifications()) {
                if (!plannedNotifications.contains(templateNotification)) {
                    MutablePlannedNotification updatedNotification = new MutablePlannedNotification();
                    updatedNotification.copy(templateNotification);
                    add(updatedNotification);
                } else {
                    MutablePlannedNotification existingNotification = findNotification(templateNotification.getUid());
                    existingNotification.update(templateNotification);
                    plannedNotifications.remove(existingNotification);
                    add(existingNotification);

                }
            }
            RuleSet templateRuleSet = template.getRuleSet();
            if (templateRuleSet != null) {
                MutableRuleSet updatedRuleSet = new MutableRuleSet();
                updatedRuleSet.copy(getRuleSet());
                updatedRuleSet.update(templateRuleSet);
                setRuleSet(updatedRuleSet);
            }
        }
        return this;
    }

    public MutablePlannedNotification findNotification(URI uid) {
        MutablePlannedNotification foundNotification = null;
        for (PlannedNotification possibleNotification : plannedNotifications) {
            if (possibleNotification.getUid().equals(uid)) {
                foundNotification = new MutablePlannedNotification();
                foundNotification.copy(possibleNotification);
            }
        }
        return foundNotification;
    }

    @Override
    public String toString() {
        return "ActionPlan [" + getName() + " : " + getDescription() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ActionPlan))
            return false;
        ActionPlan rhs = (ActionPlan) obj;
        return support.equals(this, rhs);
    }

    @Override
    public int hashCode() {
        return support.hashCode(this);
    }

    @Override
    public URI selfIdentify() {
        if (ruleset != null) ruleset.selfIdentify();
        for (PlannedNotification notification : plannedNotifications) {
            notification.selfIdentify();
        }
        return super.selfIdentify();
    }

    public void clearNotifications() {
        plannedNotifications.clear();
    }
    
    

}
