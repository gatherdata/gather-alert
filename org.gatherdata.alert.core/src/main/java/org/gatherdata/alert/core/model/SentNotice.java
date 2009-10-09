package org.gatherdata.alert.core.model;

import org.gatherdata.commons.model.UniqueEntity;

public interface SentNotice extends UniqueEntity {
    
    public DetectedEvent getDetectedEvent();
    
}
