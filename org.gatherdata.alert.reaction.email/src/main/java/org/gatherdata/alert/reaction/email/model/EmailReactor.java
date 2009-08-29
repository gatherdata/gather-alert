package org.gatherdata.alert.reaction.email.model;

import java.net.URL;
import java.util.Map;

/**
 * Interface for service which can send EmailReaction emails. 
 *  
 */
public interface EmailReactor {

	/**
	 * Prepares and sends an email.
	 * 
	 * @param mailtoUrl RFC-2368 "mailto" URL
	 * @param body main body of the email message
	 */
	public abstract void sendEmail(URL mailtoUrl, String body);

	/**
	 * Prepares an email for an EmailReaction, and sends it.
	 * 
	 * @param emailReaction
	 * @param bodyVariables
	 */
	public abstract void sendEmail(EmailReaction emailReaction, Map<String, Object> bodyVariables);

	/**
	 * Prepares an email for an EmailReaction, and sends it.
	 * 
	 * @param emailReaction
	 * @param subject
	 * @param because
	 */
	public abstract void sendEmail(EmailReaction emailReaction);
	

	
}
