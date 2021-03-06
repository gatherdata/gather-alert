/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.dao.neo4j.internal;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.model.impl.MutableActionPlan;
import org.gatherdata.commons.model.neo4j.DescribedNodeWrapper;
import org.gatherdata.commons.model.neo4j.GatherNodeWrapper;
import org.joda.time.DateTime;
import org.neo4j.api.core.Direction;
import org.neo4j.api.core.NeoService;
import org.neo4j.api.core.Node;
import org.neo4j.api.core.NotFoundException;
import org.neo4j.api.core.Relationship;
import org.neo4j.api.core.RelationshipType;
import org.neo4j.util.NodeWrapperImpl;

public class ActionPlanWrapper extends DescribedNodeWrapper implements ActionPlan, GatherNodeWrapper {

    private static Logger log = Logger.getLogger(ActionPlanWrapper.class.getName());
    
    public enum ActionPlanRelationships implements RelationshipType {
        EVENTS_OF_TYPE,
        DETECTED_BY
    }

    /**
     * 
     */
    private static final long serialVersionUID = 3942044815055659432L;

    public static final String GATHER_NODETYPE = "ActionPlan";


    private RuleSetWrapper ruleset;

    public ActionPlanWrapper(NeoService neo, Node underlyingNode) {
        super(neo, underlyingNode);
    }

    public Iterable<PlannedNotification> getNotifications() {
        // TODO Auto-generated method stub
        return null;
    }

    public RuleSet getRuleSet() {
        return ruleset;
    }

    public void setRuleSet(RuleSetWrapper ruleset) {
        this.ruleset = ruleset;

    }

    public Node getRuleSetNode() {
        return ruleset.getUnderlyingNode();
    }

}
