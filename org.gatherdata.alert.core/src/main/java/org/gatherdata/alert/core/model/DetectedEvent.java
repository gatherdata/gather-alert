/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model;

import java.net.URI;

import org.gatherdata.commons.model.UniqueEntity;
import org.joda.time.DateTime;

public interface DetectedEvent extends UniqueEntity {
    
    public DateTime getDateOfDetection();
        
    /**
     * The ActionPlan which detected the event.
     * 
     * @return
     */
    public ActionPlan getDetectedBy();
    
    /**
     * The subject which was evaluated by the
     * ruleset to indicate the event.
     * 
     * @return
     */
    public URI getIndicatedBy(); 
            
}
