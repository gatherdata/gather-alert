package org.gatherdata.alert.core.model;

import java.net.URI;

import org.gatherdata.commons.model.DescribedEntity;

public interface RuleSet extends DescribedEntity {
    
    /**
     * Returns whether this ruleset is currently active. 
     * Only active rulesets should be evaluated.
     * 
     * @return
     */
    public abstract boolean isActive();
        
    /**
     * Gets the context for this ruleset, an arbitrary
     * categorization which may be shared by multiple rule-sets.
     * 
     * @return
     */
    public abstract String getContext();
    
    /**
     * Gets the uri of the event-type which is indicated
     * by evaluating this ruleset against a subject.
     * 
     * @return
     */
    public abstract DetectableEventType getIndicatedEventType();
    
    /**
     * Whether this ruleset is satisfied by all of the predicates
     * returning true. Otherwise, the ruleset should be considered
     * satisfied by any single predicate being satisfied.
     * 
     * @return
     */
    public abstract boolean isSatisfyAll();
    
    /**
     * Returns the predicates contained within this ruleset.
     * 
     */
    public abstract Iterable<LanguageScript> getPredicates();

    public abstract int getPredicateCount();
    
}
