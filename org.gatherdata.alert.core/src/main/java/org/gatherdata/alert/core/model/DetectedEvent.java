package org.gatherdata.alert.core.model;

import java.net.URI;

import org.gatherdata.commons.model.UniqueEntity;
import org.joda.time.DateTime;

public interface DetectedEvent extends UniqueEntity {
    
    public DateTime getDateOfDetection();
    
    public DetectableEventType getEventType();
    
    /**
     * The uid of the ruleset which 
     * detected the event.
     * 
     * @return
     */
    public RuleSet getDetectedBy();
    
    /**
     * The subject which was evaluated by the
     * ruleset to indicate the event.
     * 
     * @return
     */
    public URI getIndicatedBy(); 
            
}
