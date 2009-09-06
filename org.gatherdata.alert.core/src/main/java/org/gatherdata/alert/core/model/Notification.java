package org.gatherdata.alert.core.model;

import java.net.URI;

public interface Notification {

	public abstract URI getDestination();

	public abstract String getMessage();

}