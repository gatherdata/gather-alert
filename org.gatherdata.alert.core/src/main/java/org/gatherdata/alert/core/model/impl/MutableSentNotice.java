/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model.impl;

import java.net.URI;

import org.gatherdata.alert.core.model.DetectedEvent;
import org.gatherdata.alert.core.model.SentNotice;
import org.gatherdata.commons.model.impl.MutableEntity;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

public class MutableSentNotice extends MutableEntity implements SentNotice {

	/**
	 * Auto-generated 
	 */
	private static final long serialVersionUID = -8903617950622804902L;
	
	private DetectedEvent detectedEvent;
	private URI uid;

	public DetectedEvent getDetectedEvent() {
		return this.detectedEvent;
	}
	
	public void setDetectedEvent(DetectedEvent detectedEvent) {
		this.detectedEvent = detectedEvent;
	}

	public URI getUid() {
		return this.uid;
	}
	
	public void setUid(URI uid) {
		this.uid = uid;
	}

	public static MutableSentNotice createFor(DetectedEvent detectedEvent) {
		MutableSentNotice sentNotice = new MutableSentNotice();
		
		sentNotice.setDateCreated(new DateTime());
		sentNotice.setDetectedEvent(detectedEvent);
		sentNotice.setUid(CbidFactory.createCbid(
				sentNotice.getDateCreated().toString() +
				sentNotice.getDetectedEvent().toString()
				));
		return sentNotice;
	}

}
