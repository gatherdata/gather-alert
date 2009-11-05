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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.model.UniqueEntity;
import org.gatherdata.commons.model.impl.MutableDescribedEntity;
import org.gatherdata.commons.model.impl.MutableEntity;

public class MutableRuleSet extends MutableEntity implements RuleSet {

    /**
     * 
     */
    private static final long serialVersionUID = -8055149590454066961L;
    
    private ActionPlan plan;
    private String context;
    private final Set<MutableLanguageScript> predicates = new HashSet<MutableLanguageScript>();
    private boolean isSatisfyAll;
    private boolean isActive;

    public ActionPlan getPlan() {
        return this.plan;
    }
    
    public void setPlan(ActionPlan plan) {
        this.plan = plan;
    }

    public String getContext() {
        return this.context;
    }
    
    public void setContext(String context) {
        this.context = context;
    }

    public Iterable<? extends LanguageScript> getPredicates() {
        return this.predicates;
    }
    
    public void add(MutableLanguageScript script) {
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
    
    public RuleSet copy(RuleSet template) {
        if (template != null) {
            super.copy(template);
            setActive(template.isActive());
            setSatisfyAll(template.isSatisfyAll());
            setContext(template.getContext());
            predicates.clear();
            Iterable<? extends LanguageScript> templatePredicates = template.getPredicates();
            if (templatePredicates != null) {
                for (LanguageScript predicate : templatePredicates) {
                    MutableLanguageScript copiedScript = new MutableLanguageScript();
                    copiedScript.copy(predicate);
                    add(copiedScript);
                }
            }
        }
        return this;
    }

    public RuleSet update(RuleSet template) {
        if (template != null) {
            super.update(template);
            if (template.getContext() != null) {
                setContext(template.getContext());
            }
            for (LanguageScript templatePredicate : template.getPredicates()) {
                if (!predicates.contains(templatePredicate)) {
                    MutableLanguageScript copiedScript = new MutableLanguageScript();
                    copiedScript.copy(templatePredicate);
                    add(copiedScript);
                } else {
                    MutableLanguageScript existingPredicate = findPredicate(templatePredicate.getUid());
                    if (templatePredicate.getLanguage() != null) {
                        existingPredicate.setLanguage(templatePredicate.getLanguage());
                    }
                    if (templatePredicate.getScript() != null) {
                        existingPredicate.setScript(templatePredicate.getScript());
                    }
                    predicates.remove(existingPredicate);
                    add(existingPredicate);
                }
            }
        }
        return this;
    }

    public MutableLanguageScript findPredicate(URI uid) {
        MutableLanguageScript foundPredicate = null;
        for (MutableLanguageScript possiblePredicate : predicates) {
            if (possiblePredicate.getUid().equals(uid)) {
                foundPredicate = new MutableLanguageScript();
                foundPredicate.copy(possiblePredicate);
            }
        }
        return foundPredicate;
    }

    @Override
    public String toString() {
        return "RuleSet [context=" + context + 
            ", isActive=" + isActive + ", isSatisfyAll=" + isSatisfyAll + 
            ", predicates=" + predicates + "]";
    }

    public int getPredicateCount() {
        return this.predicates.size();
    }

    @Override
    public URI selfIdentify() {
        for (LanguageScript predicate : predicates) {
            predicate.selfIdentify();
        }
        return super.selfIdentify();
    }

    public void clearPredicates() {
        predicates.clear();
    }

}
