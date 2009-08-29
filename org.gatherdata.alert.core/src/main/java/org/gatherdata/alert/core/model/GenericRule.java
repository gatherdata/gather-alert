package org.gatherdata.alert.core.model;

import javax.activation.MimeType;

public class GenericRule implements Rule {

	private boolean isActive = true;
	
	MimeType subjectType;
	String subjectQualifier;
	AlertPredicate predicate;
	Reaction action;
	
	public GenericRule(AlertPredicate predicate, Reaction action)
	{
		this.predicate = predicate;
		this.action = action;
	}
	
	public void applyTo(Object subject) {
		if (predicate.evaluate(subject))
		{
			action.reactTo(subject, predicate);
		}
	}

	public void setAction(Reaction action)
	{
		this.action = action;
	}
	
	public Reaction getAction() {
		return action;
	}

	public void setPredicate(AlertPredicate predicate)
	{
		this.predicate = predicate;
	}
	
	public AlertPredicate getPredicate() {
		return predicate;
	}

	public boolean isActive() {
	    return isActive;
    }

	public void setActive(boolean isActive) {
		this.isActive = isActive;
    }

	public String getSubjectQualifier() {
		return subjectQualifier;
    }
	
	public void setSubjectQualifier(String subjectQualifier) {
		this.subjectQualifier = subjectQualifier;
	}

	public MimeType getSubjectType() {
		return subjectType;
    }
	
	public void setSubjectType(MimeType subjectType) {
		this.subjectType = subjectType;
	}
}
