package org.gatherdata.alert.core.model.impl;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.commons.model.DescribedEntitySupport;

public class LanguageScriptSupport {

    public static boolean deepEquals(LanguageScript lhs, LanguageScript rhs) {
        boolean areEqual = true;
        
        if (lhs != rhs) {
            areEqual = DescribedEntitySupport.deepEquals(lhs, rhs);
            
            if (areEqual) {
                areEqual = new EqualsBuilder()
                    .append(lhs.getLanguage(), rhs.getLanguage())
                    .append(lhs.getScript(), rhs.getScript())
                    .isEquals();
            }
        }
        return areEqual;
    }

}
