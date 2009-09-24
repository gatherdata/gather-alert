package org.gatherdata.alert.core.model.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.model.MutableDescribedEntity;
import org.gatherdata.commons.model.MutableEntity;

public class MutableRuleSet extends MutableEntity implements RuleSet {

    private String context;
    private DetectableEventType indicatedEventType;
    private final List<LanguageScript> predicates = new ArrayList<LanguageScript>();
    private boolean isSatisfyAll;
    private boolean isActive;
    
    public String getContext() {
        return this.context;
    }
    
    public void setContext(String context) {
        this.context = context;
    }

    public DetectableEventType getIndicatedEventType() {
        return this.indicatedEventType;
    }
    
    public void setIndicatedEventType(DetectableEventType indicatedEventType) {
        this.indicatedEventType = indicatedEventType;
    }

    public Iterable<LanguageScript> getPredicates() {
        return this.predicates;
    }
    
    public void add(LanguageScript script) {
        predicates.add(script);
    }
    
    public boolean remove(LanguageScript script) {
        return predicates.remove(script);
    }

    public boolean isActive() {
        return this.isActive;
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isSatisfyAll() {
        return this.isSatisfyAll;
    }
    
    public void setSatisfyAll(boolean shouldSatisfyAll) {
        this.isSatisfyAll = shouldSatisfyAll;
    }

    @Override
    public String toString() {
        return "RuleSet [context=" + context + ", indicatedEventType=" + indicatedEventType + ", isActive="
                + isActive + ", isSatisfyAll=" + isSatisfyAll + ", predicates=" + predicates + "]";
    }

    public int getPredicateCount() {
        return this.predicates.size();
    }
    

}
