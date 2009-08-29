package org.gatherdata.alert.reaction.email.model;

import java.net.URL;
import java.util.Map;

import org.gatherdata.alert.core.model.Reaction;

public interface EmailReaction extends Reaction {

	public URL getMailtoURL();
	
	public Map<String, String> getMailHeaders();
		
	public String getBodyTemplate();
	
	public String getTemplateType();
	
}
