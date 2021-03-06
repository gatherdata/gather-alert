/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.internal;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.model.impl.MutableActionPlan;
import org.gatherdata.alert.core.spi.AlertServiceDao;
import org.gatherdata.alert.core.spi.AlertService;
import org.gatherdata.commons.model.util.IdentityGenerator;

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
        dao.beginTransaction();
        Iterable<ActionPlan> all = (Iterable<ActionPlan>) dao.getAll();
        dao.endTransaction();
        return all;
    }

    public void remove(URI uidOfActionPlan) {
        dao.remove(uidOfActionPlan);
    }

    public ActionPlan save(ActionPlan planToSave) {
        planToSave.selfIdentify();
        dao.beginTransaction();
        ActionPlan savedPlan = dao.save(planToSave);
        dao.endTransaction();
        return savedPlan;
    }

    public void update(ActionPlan planUpdate) {
        URI uidToUpdate = planUpdate.getUid();
        if (uidToUpdate != null) {
            ActionPlan currentEntity = get(uidToUpdate);
            MutableActionPlan transferEntity = new MutableActionPlan();
            transferEntity.copy(currentEntity);
            transferEntity.update(planUpdate);
            dao.save(transferEntity);
        }
    }

    public Iterable<RuleSet> getActiveRulesetsFor(String context) {
        return dao.getActiveRulesetsFor(context);
    }

}
