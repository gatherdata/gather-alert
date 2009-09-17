package org.gatherdata.alert.dao.db4o.model;

import java.net.URI;
import java.util.List;

import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.db.db4o.model.UniqueEntityDb4o;
import org.joda.time.DateTime;

public class RuleSetDb4o extends UniqueEntityDb4o implements RuleSet {

    private String context;
    private DetectableEventTypeDb4o indicatedEvent;
    private List<LanguageScriptDb4o> predicates;
    private boolean isActive;
    private boolean isSatifsyAll;

    public String getContext() {
        return this.context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public DetectableEventType getIndicatedEventType() {
        return this.indicatedEvent;
    }

    public void setIndicatedEventType(DetectableEventTypeDb4o indicatedEvent) {
        this.indicatedEvent = indicatedEvent;
    }

    public int getPredicateCount() {
        return predicates.size();
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
            setIndicatedEventType((DetectableEventTypeDb4o) new DetectableEventTypeDb4o().copy(template.getIndicatedEventType()));
            setIsActive(template.isActive());
            setIsSatisfyAll(template.isSatisfyAll());
        }
        return this;
    }

}
