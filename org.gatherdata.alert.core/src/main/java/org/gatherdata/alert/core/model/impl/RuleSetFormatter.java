package org.gatherdata.alert.core.model.impl;

import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;

public class RuleSetFormatter {

    public static String toString(RuleSet ruleSet) {
        StringBuffer formattedString = new StringBuffer();
        Iterable<LanguageScript> scripts = (Iterable<LanguageScript>) ruleSet.getPredicates();
        formattedString.append("[RuleSet " + ruleSet.getContext() + " ");
        if (scripts != null) {
            formattedString.append("[");
            for (LanguageScript script : scripts) {
                formattedString.append("{" + script.getLanguage() + ": " + script.getScript() + "}");
            }
            formattedString.append("]");
        } else {
            formattedString.append("[no predicates]");
        }
        formattedString.append("]");
        return formattedString.toString();
    }
    
    public static String toLongString(RuleSet ruleSet) {
        StringBuffer formattedString = new StringBuffer();
        Iterable<LanguageScript> scripts = (Iterable<LanguageScript>) ruleSet.getPredicates();
        formattedString.append("RuleSet " + ruleSet.getContext());
        if (scripts != null) {
            formattedString.append(", on " + (ruleSet.isSatisfyAll() ? "all" : "any") + " [");
            for (LanguageScript script : scripts) {
                formattedString.append("\t" + script.getLanguage() + ": " + script.getScript() + "; ");
            }
            formattedString.append("]");
        } else {
            formattedString.append("\tno predicate scripts!");
        }
        return formattedString.toString();
    }

}
