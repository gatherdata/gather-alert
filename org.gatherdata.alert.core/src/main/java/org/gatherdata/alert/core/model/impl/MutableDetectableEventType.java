package org.gatherdata.alert.core.model.impl;

import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.commons.model.impl.MutableDescribedEntity;

public class MutableDetectableEventType extends MutableDescribedEntity implements DetectableEventType {

    /**
     * Auto-generated serialVersionUID.
     */
    private static final long serialVersionUID = -7382440124901236355L;

    @Override
    public String toString() {
        return "DetectableEventType [description=" + description + ", name=" + name + ", uid=" + uid + "]";
    }


    
}
