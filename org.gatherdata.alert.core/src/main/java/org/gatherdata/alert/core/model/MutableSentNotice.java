package org.gatherdata.alert.core.model;

import java.net.URI;

import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

public class MutableSentNotice implements SentNotice {

	/**
	 * Auto-generated 
	 */
	private static final long serialVersionUID = -8903617950622804902L;
	
	private DateTime dateSent;
	private DetectedEvent detectedEvent;
	private URI uid;

	public DateTime getDateSent() {
		return this.dateSent;
	}
	
	public void setDateSent(DateTime dateSent) {
		this.dateSent = dateSent;
	}

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
		
		sentNotice.setDateSent(new DateTime());
		sentNotice.setDetectedEvent(detectedEvent);
		sentNotice.setUid(CbidFactory.createCbid(
				sentNotice.getDateSent().toString() +
				sentNotice.getDetectedEvent().toString()
				));
		return sentNotice;
	}

}
