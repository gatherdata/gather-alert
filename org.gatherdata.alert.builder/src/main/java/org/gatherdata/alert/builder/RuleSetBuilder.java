package org.gatherdata.alert.builder;

import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.MutableRuleSet;
import org.gatherdata.alert.core.model.RuleSet;

public class RuleSetBuilder implements FluentBuilder<RuleSet> {

    private final MutableRuleSet ruleset = new MutableRuleSet();
    
    private boolean activeWasSet = false;
        
    private RuleSetBuilder() {
        ;
    }
    
    public static RuleSetBuilder rules(String withinContext) {
        return new RuleSetBuilder().withinContext(withinContext);
    }

    private RuleSetBuilder withinContext(String withinContext) {
        ruleset.setContext(withinContext);
        return this;
    }

    public RuleSetBuilder rule(LanguageScriptBuilder languageBuilder) {
        ruleset.add(languageBuilder.build());
        return this;
    }
    
    public RuleSetBuilder active(boolean shouldBeActive) {
        ruleset.setActive(shouldBeActive);
        return this;
    }

    public RuleSet build() {
        if (!activeWasSet) ruleset.setActive(true);
        return ruleset;
    }

    protected void setEventType(DetectableEventType indicatedEventType) {
        this.ruleset.setIndicatedEventType(indicatedEventType);
    }
    
    
}
