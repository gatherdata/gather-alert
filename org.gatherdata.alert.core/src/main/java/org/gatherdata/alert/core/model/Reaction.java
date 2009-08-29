package org.gatherdata.alert.core.model;

import org.apache.commons.collections.Predicate;

/**
 * A reaction performs an action in response to a Predicate.
 *
 */
public interface Reaction {

	public abstract void reactTo(Object subject, Predicate because);
	
}
