package org.gatherdata.alert.dao.db4o.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.db.db4o.model.UniqueEntityDb4o;

public class RuleSetDb4o extends UniqueEntityDb4o implements RuleSet {

    /**
     * 
     */
    private static final long serialVersionUID = -8057901469967300031L;
    private String context;
    private List<LanguageScriptDb4o> predicates = new ArrayList<LanguageScriptDb4o>();
    private boolean isActive;
    private boolean isSatifsyAll;
    private ActionPlanDb4o plan;

    public String getContext() {
        return this.context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getPredicateCount() {
        return predicates.size();
    }

    public void addAll(Iterable<? extends LanguageScript> predicates) {
        for (LanguageScript predicate : predicates) {
            this.predicates.add(new LanguageScriptDb4o().copy(predicate));
        }
    }

    public Iterable<? extends LanguageScript> getPredicates() {
        return this.predicates;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isSatisfyAll() {
        return this.isSatifsyAll;
    }

    public void setIsSatisfyAll(boolean isSatisfyAll) {
        this.isSatifsyAll = isSatisfyAll;
    }

    public RuleSetDb4o copy(RuleSet template) {
        if (template != null) {
            super.copy(template);
            setContext(template.getContext());
            setIsActive(template.isActive());
            setIsSatisfyAll(template.isSatisfyAll());
            addAll(template.getPredicates());
        }
        return this;
    }
    
    public ActionPlan getPlan() {
        return plan;
    }

    public void setPlan(ActionPlanDb4o plan) {
        this.plan = plan;
    }

}
