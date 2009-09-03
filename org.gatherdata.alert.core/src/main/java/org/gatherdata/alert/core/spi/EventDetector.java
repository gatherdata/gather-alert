package org.gatherdata.alert.core.spi;

import java.util.Map;

import org.gatherdata.alert.core.model.DetectedEvent;
import org.gatherdata.alert.core.model.RuleSet;

public interface EventDetector {

    public Iterable<DetectedEvent> detect(Iterable<RuleSet> usingRules, Map<String, Object> attributes);
    
}
