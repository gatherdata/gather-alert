package org.gatherdata.alert.dao.neo4j.internal;

import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.db.neo4j.NeoServices;
import org.gatherdata.commons.model.neo4j.GatherNodeWrapper;
import org.gatherdata.commons.model.neo4j.NodeAdapter;
import org.neo4j.api.core.Direction;
import org.neo4j.api.core.NeoService;
import org.neo4j.api.core.Node;
import org.neo4j.api.core.Relationship;

public class RuleSetNodeAdapter implements NodeAdapter<RuleSet, RuleSetWrapper> {

    public RuleSet adaptFromNode(Node nodeToAdapt, NeoService neo) {
        RuleSetWrapper rulesetWrapper = null;

        if (RuleSetWrapper.GATHER_NODETYPE.equals(nodeToAdapt.getProperty(GatherNodeWrapper.GATHER_NODETYPE_PROPERTY))) {
            rulesetWrapper = new RuleSetWrapper(neo, nodeToAdapt);
            
            // get the eventType
            Relationship eventTypeRelationship = nodeToAdapt.getSingleRelationship(RuleSetWrapper.RuleSetRelationships.DETECTS_EVENTS_OF_TYPE, Direction.OUTGOING);
            DetectableEventTypeWrapper eventTypeWrapper = new DetectableEventTypeWrapper(neo, eventTypeRelationship.getEndNode());
            rulesetWrapper.setEventType(eventTypeWrapper);
            
            // attach predicates
            for (Relationship predicateRelationship : nodeToAdapt.getRelationships(RuleSetWrapper.RuleSetRelationships.EVALUATES_WITH)) {
                Node predicateNode = predicateRelationship.getEndNode();
                LanguageScriptWrapper predicateWrapper = new LanguageScriptWrapper(neo, predicateNode);
                rulesetWrapper.add(predicateWrapper);
            }
        }
        return rulesetWrapper;
    }

    public RuleSetWrapper deriveInstanceFrom(RuleSet ruleset, NeoService neo) {
        Node rulesetNode = neo.createNode();
        rulesetNode.setProperty(GatherNodeWrapper.GATHER_NODETYPE_PROPERTY, RuleSetWrapper.GATHER_NODETYPE);

        RuleSetWrapper rulesetWrapper = RuleSetWrapper.newInstance(RuleSetWrapper.class, neo, rulesetNode);
        rulesetWrapper.setUid(ruleset.getUid());
        rulesetWrapper.setDateCreated(ruleset.getDateCreated());
        rulesetWrapper.setContext(ruleset.getContext());
        rulesetWrapper.setActive(ruleset.isActive());
        rulesetWrapper.setSatisfyAll(ruleset.isSatisfyAll());

        for (LanguageScript predicate : ruleset.getPredicates()) {
            Node predicateNode = neo.createNode();
            rulesetNode.createRelationshipTo(predicateNode, RuleSetWrapper.RuleSetRelationships.EVALUATES_WITH);
            LanguageScriptWrapper languageScriptWrapper = LanguageScriptWrapper.newInstance(
                    LanguageScriptWrapper.class, neo, predicateNode);
            languageScriptWrapper.setUid(predicate.getUid());
            languageScriptWrapper.setName(predicate.getName());
            languageScriptWrapper.setDescription(predicate.getDescription());
            languageScriptWrapper.setDateCreated(predicate.getDateCreated());
            rulesetWrapper.add(languageScriptWrapper);
        }
        
        return rulesetWrapper;
    }

}
