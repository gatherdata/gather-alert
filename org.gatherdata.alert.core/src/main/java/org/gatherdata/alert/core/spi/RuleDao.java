package org.gatherdata.alert.core.spi;

import java.util.Collection;

import javax.activation.MimeType;

import org.gatherdata.alert.core.model.Rule;

public interface RuleDao {

	Collection<? extends Rule> getRulesFor(MimeType type, String qualifier);

}
