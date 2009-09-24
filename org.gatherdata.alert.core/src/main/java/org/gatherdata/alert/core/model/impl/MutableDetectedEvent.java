package org.gatherdata.alert.core.model.impl;

import java.net.URI;

import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.DetectedEvent;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.commons.model.impl.MutableEntity;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

public class MutableDetectedEvent extends MutableEntity implements DetectedEvent {

    private DateTime dateOfDetection;
    private RuleSet ruleset;
    private DetectableEventType eventType;
    private URI subjectUid;

    public DateTime getDateOfDetection() {
        return this.dateOfDetection;
    }
    
    public void setDateOfDetection(DateTime dateOfDetection) {
        this.dateOfDetection = dateOfDetection;
    }

    public RuleSet getDetectedBy() {
        return this.ruleset;
    }

    public void setDetectedBy(RuleSet ruleset) {
        this.ruleset = ruleset;
    }

    public DetectableEventType getEventType() {
        return this.eventType;
    }

    public void setEventType(DetectableEventType eventType) {
        this.eventType = eventType;
    }

    public URI getIndicatedBy() {
        return this.subjectUid;
    }

    public void setIndicatedBy(URI subjectUid) {
        this.subjectUid = subjectUid;
    }

    @Override
    public String toString() {
        return "DetectedEvent [dateOfDetection=" + dateOfDetection + ", eventType=" + eventType + ", ruleset="
                + ruleset + ", subjectUid=" + subjectUid + "]";
    }

    public static CbidFactory uidFactory = new CbidFactory();
    
	public static DetectedEvent createFor(DateTime detectionDate, URI subjectUid, RuleSet ruleset) {
		MutableDetectedEvent detectedEvent = new MutableDetectedEvent();
		detectedEvent.setEventType(ruleset.getIndicatedEventType());
		detectedEvent.setDateOfDetection(detectionDate);
		detectedEvent.setDetectedBy(ruleset);
		detectedEvent.setIndicatedBy(subjectUid);
		detectedEvent.setUid(uidFactory.createCbid(detectedEvent.getEventType().toString() + detectedEvent.getDateOfDetection()));
		return detectedEvent;
	}
    
}
