package org.gatherdata.alert.reaction.email.model;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.Predicate;

public class GenericEmailReaction implements EmailReaction {
	
	private EmailReactor emailReactor;
		
	private String bodyTemplate;

	private URL mailtoUrl;

	private String templateType;

	private Map<String, String> headers;
	
	public GenericEmailReaction(EmailReactor emailReactor)
	{
		this.emailReactor = emailReactor;
	}

	public void reactTo(Object subject, Predicate because) {
		Map<String, Object> bodyVariables = new HashMap<String, Object>();
		bodyVariables.put("subject", subject);
		bodyVariables.put("cause", because);
		emailReactor.sendEmail(this, bodyVariables);
    }

	public void setReactor(EmailReactor emailReactor) {
		this.emailReactor = emailReactor;
    }

	public void setBodyTemplate(String bodyTemplate) {
		this.bodyTemplate = bodyTemplate;
	}
	
	public String getBodyTemplate() {
		return bodyTemplate;
    }

	public void setMailHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	
	public Map<String, String> getMailHeaders() {
		if (headers == null) headers = new HashMap<String, String>();
		return headers;
	}

	public URL getMailtoURL() {
		return mailtoUrl;
    }
	
	public void setMailtoURL(URL mailtoUrl) {
		this.mailtoUrl = mailtoUrl;
		addMissingHeadersFromUrl(mailtoUrl);
	}

	/**
	 * Adds header values extracted from a mailto: url. 
	 * This will not override any existing headers. It will
	 * only add headers that have not already been defined.
	 * 
	 * @param toUrl
	 */
	private void addMissingHeadersFromUrl(URL toUrl) {
	    MailtoUrl asMailtoUrl = new MailtoUrl(toUrl);
	    Map<String,String> headersFromUrl = asMailtoUrl.getHeaderMap();
	    Map<String,String> definedHeaders = getMailHeaders();
	    for (String key : headersFromUrl.keySet())
	    {
	    	if (!definedHeaders.containsKey(key)) definedHeaders.put(key, headersFromUrl.get(key));
	    }
    }

	public String getTemplateType() {
	    return templateType;
    }

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

    public String getDestination() {
        // TODO Auto-generated method stub
        return null;
    }

    public URI getNotifierUid() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getTemplate() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    public URI getUid() {
        // TODO Auto-generated method stub
        return null;
    }
}
