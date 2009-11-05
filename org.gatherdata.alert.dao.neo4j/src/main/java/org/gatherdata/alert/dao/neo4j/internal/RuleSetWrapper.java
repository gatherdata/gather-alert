/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.dao.neo4j.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.model.neo4j.GatherNodeWrapper;
import org.gatherdata.commons.model.neo4j.UniqueNodeWrapper;
import org.neo4j.api.core.NeoService;
import org.neo4j.api.core.Node;
import org.neo4j.api.core.RelationshipType;

public class RuleSetWrapper extends UniqueNodeWrapper implements RuleSet, GatherNodeWrapper {

    public static final String GATHER_NODETYPE = "RuleSet";
    public static final String CONTEXT_PROPERTY = "context";
    public static final String IS_ACTIVE_PROPERTY = "active";
    private static final String IS_SATISFY_ALL_PROPERTY = "satisfyAll";

    private Collection<LanguageScriptWrapper> predicates = new ArrayList<LanguageScriptWrapper>();

    public enum RuleSetRelationships implements RelationshipType {
        DETECTS_EVENTS_OF_TYPE, // relationship to EventType node
        EVALUATES_WITH
    }

    public RuleSetWrapper(NeoService neo, Node underlyingNode) {
        super(neo, underlyingNode);
    }

    public String getContext() {
        return (String) getUnderlyingNode().getProperty(CONTEXT_PROPERTY);
    }

    public void setContext(String context) {
        getUnderlyingNode().setProperty(CONTEXT_PROPERTY, context);
    }

    public int getPredicateCount() {
        return predicates.size();
    }

    public Iterable<LanguageScript> getPredicates() {
        getUnderlyingNode().getRelationships(RuleSetRelationships.EVALUATES_WITH);
        return null;
    }

    public boolean isActive() {
        return (Boolean)getUnderlyingNode().getProperty(IS_ACTIVE_PROPERTY, Boolean.FALSE);
    }

    public void setActive(boolean active) {
        getUnderlyingNode().setProperty(IS_ACTIVE_PROPERTY, Boolean.valueOf(active));
    }
    
    public boolean isSatisfyAll() {
        return (Boolean)getUnderlyingNode().getProperty(IS_SATISFY_ALL_PROPERTY, Boolean.FALSE);
    }

    public void setSatisfyAll(boolean satisfyAll) {
        getUnderlyingNode().setProperty(IS_SATISFY_ALL_PROPERTY, Boolean.valueOf(satisfyAll));
    }
    
    public void add(LanguageScriptWrapper languageScript) {
        this.predicates.add(languageScript);
    }

    public ActionPlan getPlan() {
        // TODO Auto-generated method stub
        return null;
    }

}
