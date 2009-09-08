package org.gatherdata.alert.dao.neo4j.internal;

import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.commons.model.neo4j.DescribedNodeWrapper;
import org.neo4j.api.core.NeoService;
import org.neo4j.api.core.Node;

public class LanguageScriptWrapper extends DescribedNodeWrapper implements LanguageScript {

    private static final String LANGUAGE_PROPERTY = "language";

    public LanguageScriptWrapper(NeoService neo, Node underlyingNode) {
        super(neo, underlyingNode);
    }

    public String getLanguage() {
        return (String)getUnderlyingNode().getProperty(LANGUAGE_PROPERTY);
    }

    public String getScript() {
        return (String)getUnderlyingNode().getProperty(LANGUAGE_PROPERTY);
    }


}
