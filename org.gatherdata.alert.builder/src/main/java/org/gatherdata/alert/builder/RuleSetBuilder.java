/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.builder;

import org.apache.commons.lang.NullArgumentException;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.model.impl.MutableRuleSet;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

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

    public RuleSetBuilder satisfyAll(boolean shouldSatisfyAll) {
        ruleset.setSatisfyAll(shouldSatisfyAll);
        return this;
    }
    
    public MutableRuleSet build() {
        if (!activeWasSet) ruleset.setActive(true);
        if (ruleset.getDateCreated() == null) {
            ruleset.setDateCreated(new DateTime());
        }
        if (!(ruleset.getPredicateCount() > 0)) {
            throw new NullArgumentException("rule");
        }
        if (ruleset.getUid() == null) {
            StringBuffer predicateBuffer = new StringBuffer();
            for (LanguageScript predicate : ruleset.getPredicates()) {
                predicateBuffer.append(predicate.getLanguage());
                predicateBuffer.append(predicate.getScript());
            }
            ruleset.setUid(CbidFactory.createCbid(
                    ruleset.getContext() + ruleset.getDateCreated().toString() + predicateBuffer.toString()
                    ));
        }

        return ruleset;
    }    
    
}
