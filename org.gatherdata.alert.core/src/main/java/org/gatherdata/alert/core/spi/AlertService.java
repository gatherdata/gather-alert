package org.gatherdata.alert.core.spi;

import java.util.Collection;

import javax.activation.MimeType;

import org.gatherdata.alert.core.model.Rule;

/**
 * A service which accepts Envelopes, applying rules to determine whether a
 * notification should be sent.
 *
 */
public interface AlertService {

	public Collection<Rule> getRulesFor(MimeType type, String qualifier);
	
}
