package org.gatherdata.alert.core.model;

import org.gatherdata.commons.model.MutableDescribedEntity;

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
