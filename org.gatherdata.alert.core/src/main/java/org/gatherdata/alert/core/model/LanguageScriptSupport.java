package org.gatherdata.alert.core.model;

import org.apache.commons.lang.builder.EqualsBuilder;

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
