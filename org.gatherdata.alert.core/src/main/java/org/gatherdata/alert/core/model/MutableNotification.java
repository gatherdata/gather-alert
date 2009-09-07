package org.gatherdata.alert.core.model;

import java.net.URI;

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
