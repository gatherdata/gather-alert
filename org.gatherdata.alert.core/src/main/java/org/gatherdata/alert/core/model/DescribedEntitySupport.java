package org.gatherdata.alert.core.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.gatherdata.commons.model.DescribedEntity;

public class DescribedEntitySupport {

    public static boolean deepEquals(DescribedEntity lhs, DescribedEntity rhs) {
        boolean areEqual = true;
        if (lhs != rhs) {
            areEqual = new EqualsBuilder()
                .append(lhs.getUid(), rhs.getUid())
                .append(lhs.getName(), rhs.getName())
                .append(lhs.getDescription(), rhs.getDescription())
                .append(lhs.getDateCreated(), rhs.getDateCreated())
                .isEquals();
        }
        return areEqual;
    }

}
