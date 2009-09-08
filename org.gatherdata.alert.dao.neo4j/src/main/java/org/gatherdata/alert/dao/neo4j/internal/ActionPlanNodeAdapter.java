package org.gatherdata.alert.dao.neo4j.internal;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.dao.neo4j.internal.ActionPlanWrapper.ActionPlanRelationships;
import org.gatherdata.commons.db.neo4j.NeoServices;
import org.gatherdata.commons.model.neo4j.GatherNodeWrapper;
import org.gatherdata.commons.model.neo4j.NodeAdapter;
import org.gatherdata.commons.model.neo4j.UniqueNodeWrapper;
import org.joda.time.DateTime;
import org.neo4j.api.core.Direction;
import org.neo4j.api.core.NeoService;
import org.neo4j.api.core.Node;
import org.neo4j.api.core.Relationship;

public class ActionPlanNodeAdapter implements NodeAdapter<ActionPlan, ActionPlanWrapper> {

    private NodeAdapter<RuleSet, RuleSetWrapper> rulesetAdapter = new RuleSetNodeAdapter();
    
    public ActionPlan adaptFromNode(Node nodeToAdapt, NeoService neo) {
        ActionPlanWrapper derivedInstance = null;

        if (ActionPlanWrapper.GATHER_NODETYPE.equals(nodeToAdapt.getProperty(ActionPlanWrapper.GATHER_NODETYPE_PROPERTY))) {
            derivedInstance = new ActionPlanWrapper(neo, nodeToAdapt);
            
            // attach the eventType
            Relationship eventTypeRelationship = nodeToAdapt.getSingleRelationship(ActionPlanWrapper.ActionPlanRelationships.EVENTS_OF_TYPE, Direction.OUTGOING);
            DetectableEventTypeWrapper eventTypeWrapper = new DetectableEventTypeWrapper(neo, eventTypeRelationship.getEndNode());
            derivedInstance.setEventType(eventTypeWrapper);
            
            // attach the ruleset
            Relationship rulesetRelationship = nodeToAdapt.getSingleRelationship(ActionPlanWrapper.ActionPlanRelationships.EVENTS_OF_TYPE, Direction.OUTGOING);
            Node rulesetNode = rulesetRelationship.getEndNode();
            RuleSet ruleset = rulesetAdapter.adaptFromNode(rulesetNode, neo);
        }

        return derivedInstance;
    }

    public ActionPlanWrapper deriveInstanceFrom(ActionPlan template, NeoService neo) {
        Node actionPlanNode = neo.createNode();
        actionPlanNode.setProperty(GatherNodeWrapper.GATHER_NODETYPE_PROPERTY, ActionPlanWrapper.GATHER_NODETYPE);
        
        ActionPlanWrapper actionPlanWrapper = new ActionPlanWrapper(neo, actionPlanNode);
        actionPlanWrapper.setUid(template.getUid());
        actionPlanWrapper.setDateCreated(template.getDateCreated());
        actionPlanWrapper.setName(template.getName());
        actionPlanWrapper.setDescription(template.getDescription());
        
        DetectableEventType eventType = template.getEventType();
        Node eventTypeNode = neo.createNode();
        eventTypeNode.setProperty(GatherNodeWrapper.GATHER_NODETYPE_PROPERTY, DetectableEventTypeWrapper.GATHER_NODETYPE);

        DetectableEventTypeWrapper eventTypeWrapper = DetectableEventTypeWrapper.newInstance(DetectableEventTypeWrapper.class, neo, eventTypeNode);
        eventTypeWrapper.setDateCreated(eventType.getDateCreated());
        eventTypeWrapper.setName(eventType.getName());
        eventTypeWrapper.setDescription(eventType.getDescription());
        eventTypeWrapper.setUid(eventType.getUid());
        
        actionPlanWrapper.setEventType(eventTypeWrapper);
        actionPlanNode.createRelationshipTo( eventTypeNode, ActionPlanWrapper.ActionPlanRelationships.EVENTS_OF_TYPE );

        
        RuleSet ruleset = template.getRuleSet();
        RuleSetWrapper rulesetWrapper = rulesetAdapter.deriveInstanceFrom(ruleset, neo);
        
        actionPlanWrapper.setRuleSet(rulesetWrapper);
        actionPlanNode.createRelationshipTo( rulesetWrapper.getUnderlyingNode(), ActionPlanWrapper.ActionPlanRelationships.DETECTED_BY );

        rulesetWrapper.setIndicatedEventType(eventTypeWrapper);
        
        return actionPlanWrapper;
    }

}