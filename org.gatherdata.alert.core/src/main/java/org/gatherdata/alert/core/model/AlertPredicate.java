package org.gatherdata.alert.core.model;

import java.net.URI;

import org.apache.commons.collections.Predicate;

public interface AlertPredicate extends Predicate {

	public abstract URI getUid();
	
}
