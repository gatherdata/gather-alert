package org.gatherdata.alert.core.model.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.gatherdata.commons.model.DescribedEntitySupport;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;

public class RuleSetSupport {

    public static boolean deepEquals(RuleSet lhs, RuleSet rhs) {
        boolean areEqual = true;

        if (lhs != rhs) {
            areEqual = new EqualsBuilder().append(lhs.getUid(), rhs.getUid()).append(lhs.getDateCreated(),
                    rhs.getDateCreated()).append(lhs.getContext(), rhs.getContext()).isEquals();

            if (areEqual) {
                areEqual = DescribedEntitySupport.deepEquals(lhs.getIndicatedEventType(), rhs.getIndicatedEventType());

                if (areEqual) {
                    Map<URI, LanguageScript> lhsPredicateMap = new HashMap<URI, LanguageScript>();
                    for (LanguageScript predicate : lhs.getPredicates()) {
                        lhsPredicateMap.put(predicate.getUid(), predicate);
                    }
                    Map<URI, LanguageScript> rhsPredicateMap = new HashMap<URI, LanguageScript>();
                    for (LanguageScript predicate : rhs.getPredicates()) {
                        rhsPredicateMap.put(predicate.getUid(), predicate);
                    }
                    areEqual = lhsPredicateMap.size() == rhsPredicateMap.size();
                    if (areEqual) { // check RuleSets
                        for (URI key : lhsPredicateMap.keySet()) {
                            LanguageScript lhsPredicate = lhsPredicateMap.get(key);
                            LanguageScript rhsPredicate = rhsPredicateMap.get(key);

                            areEqual = LanguageScriptSupport.deepEquals(lhsPredicate, rhsPredicate);

                        }
                    }

                }
            }

        }
        return areEqual;
    }

}
