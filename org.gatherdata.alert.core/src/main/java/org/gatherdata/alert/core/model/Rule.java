package org.gatherdata.alert.core.model;

import javax.activation.MimeType;


public interface Rule {

	public abstract MimeType getSubjectType();
	
	public abstract String getSubjectQualifier();
	
	public abstract AlertPredicate getPredicate();
	
	public abstract Reaction getAction();
	
	public abstract void applyTo(Object subject);

	public boolean isActive();
	
	public void setActive(boolean isActive);
	
}
