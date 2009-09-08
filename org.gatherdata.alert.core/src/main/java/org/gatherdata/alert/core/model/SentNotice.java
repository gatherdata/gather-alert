package org.gatherdata.alert.core.model;

import org.gatherdata.commons.model.UniqueEntity;
import org.joda.time.DateTime;

public interface SentNotice extends UniqueEntity {
    
    public DetectedEvent getDetectedEvent();
    
}
