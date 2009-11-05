/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model;

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
