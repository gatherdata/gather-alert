package org.gatherdata.alert.reaction.email.spi;

import java.net.URI;
import java.net.URL;

import org.gatherdata.alert.core.spi.ReactionProvider;
import org.gatherdata.alert.reaction.email.model.EmailReactor;
import org.gatherdata.alert.reaction.email.model.GenericEmailReaction;

/**
 * Public API for service which can send email and supplies Reactions
 * which may be paired with AlertPredicates into Rules.
 * 
 */
public interface EmailReactionService extends ReactionProvider, EmailReactor
{

	/**
	 * Sets the port number to use for outgoing smtp connections.
	 * 
	 * Defaults to 25.
	 * 
	 * @param portNumber port number to use
	 */
	public abstract void setSmtpPort(int portNumber);
	
	/**
	 * Sets the hostname to use for outgoing smtp connections.
	 * 
	 * Defaults to localhost
	 * 
	 * @param hostName
	 */
	public abstract void setSmtpHostName(String hostName);

}

