package org.gatherdata.alert.core.spi;

import java.net.URI;
import java.util.List;

import org.gatherdata.alert.core.model.AlertPredicate;

/**
 * Provides a collection of AlertPredicates. 
 *
 */
public interface PredicateProvider {

	String getProviderIdentifier();
	
    List<AlertPredicate> getAll();
    
	List<URI> getAllUids();

    AlertPredicate get(URI id);

}
