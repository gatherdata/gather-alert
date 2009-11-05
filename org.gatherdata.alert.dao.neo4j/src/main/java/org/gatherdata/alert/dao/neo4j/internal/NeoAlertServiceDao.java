/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.dao.neo4j.internal;

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
import org.gatherdata.commons.db.neo4j.NeoServices;
import org.gatherdata.commons.model.neo4j.GatherNodeWrapper;
import org.gatherdata.commons.model.neo4j.IterableNodeWrapper;
import org.gatherdata.commons.model.neo4j.NodeAdapter;
import org.gatherdata.commons.model.neo4j.UniqueNodeWrapper;
import org.neo4j.api.core.Node;
import org.neo4j.api.core.NotFoundException;
import org.neo4j.api.core.Transaction;
import org.neo4j.util.index.LuceneIndexService;

import com.google.inject.Inject;

public class NeoAlertServiceDao implements AlertServiceDao {

    public static Log log = LogFactory.getLog(NeoAlertServiceDao.class);

    @Inject
    NeoServices neo;

    private Transaction currentTransaction;

    private NodeAdapter<ActionPlan, ActionPlanWrapper> actionPlanAdapter = new ActionPlanNodeAdapter();
    
    private NodeAdapter<RuleSet, RuleSetWrapper> rulesetAdapter = new RuleSetNodeAdapter();

    public NeoAlertServiceDao() {
        ;
    }

    public Iterable<RuleSet> getActiveRulesetsFor(String context) {
        log.debug("getActiveRulesetsFor(" + context + ")");
        List<RuleSet> activeRules = Collections.emptyList();
        if (context != null) {
            activeRules = new ArrayList<RuleSet>();
            Iterable<Node> allNodes = neo.indexService().getNodes(GatherNodeWrapper.GATHER_NODETYPE_PROPERTY,
                    RuleSetWrapper.GATHER_NODETYPE);
            for (Node possibleNode : allNodes) {
                log.debug("\t" + possibleNode);
                if (context.equals(possibleNode.getProperty(RuleSetWrapper.CONTEXT_PROPERTY))) {
                    if ((Boolean) possibleNode.getProperty(RuleSetWrapper.IS_ACTIVE_PROPERTY)) {
                        activeRules.add(rulesetAdapter.adaptFromNode(possibleNode, neo.neo()));
                    }
                }
            }
        }
        return activeRules;
    }

    public boolean exists(URI uid) {
        Node foundNode = null;
        if (uid != null) {
            try {
                foundNode = neo.indexService().getSingleNode(UniqueNodeWrapper.UID_PROPERTY, uid.toASCIIString());
            } catch (NotFoundException nfe) {
                ;
            } // not found means it doesn't exist
        }
        return (foundNode != null);
    }

    public ActionPlan get(URI uidOfActionPlan) {
        ActionPlan foundEntity = null;
        Node foundNode = neo.indexService().getSingleNode(UniqueNodeWrapper.UID_PROPERTY,
                uidOfActionPlan.toASCIIString());
        if (foundNode != null) {
            foundEntity = actionPlanAdapter.adaptFromNode(foundNode, neo.neo());
        }
        return foundEntity;
    }

    public Iterable<ActionPlan> getAll() {
        Iterable<Node> allNodes = neo.indexService().getNodes(GatherNodeWrapper.GATHER_NODETYPE_PROPERTY,
                ActionPlanWrapper.GATHER_NODETYPE);

        return new IterableNodeWrapper(neo.neo(), allNodes, actionPlanAdapter);
    }
    
    public int getCount() {
        int count = 0;
        beginTransaction();
        Iterable<Node> allNodes = neo.indexService().getNodes(GatherNodeWrapper.GATHER_NODETYPE_PROPERTY,
                ActionPlanWrapper.GATHER_NODETYPE);
        for (Node n : allNodes) {
            count++;
        }
        endTransaction();
        return count;
    }

    public void remove(URI uidOfActionPlan) {
        // TODO Auto-generated method stub

    }

    public ActionPlan save(ActionPlan planToSave) {
        ActionPlanWrapper savedInstance = null;
        Transaction tx = neo.neo().beginTx();
        try {
            if (!exists(planToSave.getUid())) {
                savedInstance = actionPlanAdapter.deriveInstanceFrom(planToSave, neo.neo());
                index(savedInstance.getUnderlyingNode());
                index(savedInstance.getRuleSetNode());
            }
            tx.success();
        } finally {
            tx.finish();
        }
        return savedInstance;
    }

    public void beginTransaction() {
        this.currentTransaction = neo.neo().beginTx();

    }

    public void endTransaction() {
        this.currentTransaction.success();

    }
    
    private void index(Node nodeToIndex) {
        // index by uid
        neo.indexService().index(nodeToIndex, UniqueNodeWrapper.UID_PROPERTY,
                nodeToIndex.getProperty(UniqueNodeWrapper.UID_PROPERTY));

        // index by node type
        neo.indexService().index(nodeToIndex, GatherNodeWrapper.GATHER_NODETYPE_PROPERTY,
                nodeToIndex.getProperty(GatherNodeWrapper.GATHER_NODETYPE_PROPERTY));
    }

}
