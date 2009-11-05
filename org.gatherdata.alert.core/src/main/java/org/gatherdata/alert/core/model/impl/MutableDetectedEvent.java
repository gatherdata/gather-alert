/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model.impl;

import java.net.URI;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectedEvent;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.model.impl.MutableEntity;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

public class MutableDetectedEvent extends MutableEntity implements DetectedEvent {

    /**
     * 
     */
    private static final long serialVersionUID = 6045558510658964119L;
    
    private DateTime dateOfDetection;
    private ActionPlan actionPlan;
    private URI subjectUid;

    public DateTime getDateOfDetection() {
        return this.dateOfDetection;
    }
    
    public void setDateOfDetection(DateTime dateOfDetection) {
        this.dateOfDetection = dateOfDetection;
    }

    public ActionPlan getDetectedBy() {
        return this.actionPlan;
    }

    public void setDetectedBy(ActionPlan actionPlan) {
        this.actionPlan = actionPlan;
    }
    public URI getIndicatedBy() {
        return this.subjectUid;
    }

    public void setIndicatedBy(URI subjectUid) {
        this.subjectUid = subjectUid;
    }

    @Override
    public String toString() {
        return "DetectedEvent [dateOfDetection=" + dateOfDetection + ", actionPlan=" + actionPlan + 
            ", subjectUid=" + subjectUid + "]";
    }

    public static CbidFactory uidFactory = new CbidFactory();
    
	public static DetectedEvent createFor(DateTime detectionDate, URI subjectUid, RuleSet ruleset) {
		MutableDetectedEvent detectedEvent = new MutableDetectedEvent();
		detectedEvent.setDateOfDetection(detectionDate);
		detectedEvent.setIndicatedBy(subjectUid);
		detectedEvent.setDetectedBy(ruleset.getPlan());
		detectedEvent.setUid(CbidFactory.createCbid(detectedEvent.getDetectedBy().toString() + detectedEvent.getDateOfDetection()));
		return detectedEvent;
	}
    
}
