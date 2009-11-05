/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model.impl;

import java.net.URI;

import org.gatherdata.alert.core.model.Notification;

public class MutableNotification implements Notification {
    private URI destination;
	private String message;

	public MutableNotification() {
		;
	}
	
	public MutableNotification(URI destination, String message) {
		setDestination(destination);
		setMessage(message);
	}
	
	/* (non-Javadoc)
	 * @see org.gatherdata.alert.core.model.Notification#getDestination()
	 */
	public URI getDestination() {
    	return this.destination;
    }
    
    public void setDestination(URI destination) {
    	this.destination = destination;
    }
    
    /* (non-Javadoc)
	 * @see org.gatherdata.alert.core.model.Notification#getMessage()
	 */
    public String getMessage() {
    	return this.message;
    }
    
    public void setMessage(String message) {
    	this.message = message;
    }

    @Override
    public String toString() {
        return "destination: " + destination + "\nmessage:\n" + message;
    }

    
}
