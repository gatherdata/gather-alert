package org.gatherdata.alert.core.model;

import java.net.URI;

import org.apache.commons.collections.Predicate;
import org.gatherdata.commons.model.DescribedEntity;

/**
 * A ScriptedPredicate is a predicate which hosts a script
 * to evaluate a subject, expected to return true if the subject 
 * satisfies the criteria expressed in the script, and 
 * false otherwise.
 *
 */
public interface LanguageScript extends DescribedEntity {
	
	public abstract String getLanguage();
	
	public abstract String getScript();
	
}
