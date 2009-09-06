package org.gatherdata.alert.core.model;

import java.net.URI;

public class MutableNotification {
    private URI destination;
	private String message;

	public MutableNotification() {
		;
	}
	
	public MutableNotification(URI destination, String message) {
		setDestination(destination);
		setMessage(message);
	}
	
	public URI getDestination() {
    	return this.destination;
    }
    
    public void setDestination(URI destination) {
    	this.destination = destination;
    }
    
    public String getMessage() {
    	return this.message;
    }
    
    public void setMessage(String message) {
    	this.message = message;
    }

}
