package org.gatherdata.alert.core.internal;

import java.net.URI;

import org.apache.commons.collections.Predicate;
import org.gatherdata.alert.core.model.AlertPredicate;

public class BuiltInPredicate implements AlertPredicate {

	private URI uid;
	private Predicate predicate;

	public BuiltInPredicate(URI uid, Predicate predicate)
	{
		this.uid = uid;
		this.predicate = predicate;
	}

	public URI getUid() {
		return uid;
    }

	public boolean evaluate(Object subject) {
		return predicate.evaluate(subject);
    }
}
