package org.gatherdata.alert.dao.neo4j.internal;

import java.net.URI;
import java.net.URISyntaxException;

import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.commons.model.neo4j.DescribedNodeWrapper;
import org.gatherdata.commons.model.neo4j.GatherNodeWrapper;
import org.joda.time.DateTime;
import org.neo4j.api.core.NeoService;
import org.neo4j.api.core.Node;
import org.neo4j.api.core.NotFoundException;
import org.neo4j.util.NodeWrapperImpl;

public class DetectableEventTypeWrapper extends DescribedNodeWrapper implements DetectableEventType, GatherNodeWrapper {

    /**
     * 
     */
    private static final long serialVersionUID = 6346616737159422897L;
    
    public static final String GATHER_NODETYPE = "DetectableEventType";

    public DetectableEventTypeWrapper(NeoService neo, Node underlyingNode) {
        super(neo, underlyingNode);
    }


}
