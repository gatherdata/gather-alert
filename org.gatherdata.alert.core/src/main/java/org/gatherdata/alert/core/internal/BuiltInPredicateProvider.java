package org.gatherdata.alert.core.internal;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.functors.FalsePredicate;
import org.apache.commons.collections.functors.TruePredicate;
import org.gatherdata.alert.core.model.AlertPredicate;
import org.gatherdata.alert.core.spi.PredicateProvider;

public class BuiltInPredicateProvider implements PredicateProvider {
	
	private static final String PREDICATE_PROVIDER_ID = "builtin";
	
	private static Map<URI, AlertPredicate> predicateMap = new HashMap<URI, AlertPredicate>();
	private List<AlertPredicate> allPredicates = null;
	private ArrayList<URI> allIds = null;
	
	public static URI ALWAYS_TRUE_UID;
	public static URI ALWAYS_FALSE_UID;
	
	static 
	{
		ALWAYS_TRUE_UID = BuiltInUriFactory.createUriFor("predicate", "always_true");
		ALWAYS_FALSE_UID = BuiltInUriFactory.createUriFor("predicate", "always_false");

		predicateMap.put(ALWAYS_TRUE_UID, new BuiltInPredicate(ALWAYS_TRUE_UID, TruePredicate.INSTANCE));
		predicateMap.put(ALWAYS_FALSE_UID, new BuiltInPredicate(ALWAYS_FALSE_UID, FalsePredicate.INSTANCE));
	}

	public String getProviderIdentifier() {
	    return PREDICATE_PROVIDER_ID;
    }
	
	public AlertPredicate get(URI id) {
		return predicateMap.get(id);
    }

	public List<AlertPredicate> getAll() {
		if (allPredicates == null) {
			allPredicates = new ArrayList<AlertPredicate>();
			allPredicates.addAll(predicateMap.values());
		}
		return allPredicates;
    }
	
	public List<URI> getAllUids() {
		if (allIds == null) {
			allIds = new ArrayList<URI>();
			allIds.addAll(predicateMap.keySet());
		}
		return allIds;
	}

}
