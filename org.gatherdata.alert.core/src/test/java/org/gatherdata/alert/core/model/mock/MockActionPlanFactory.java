package org.gatherdata.alert.core.model.mock;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.model.impl.MutableActionPlan;
import org.gatherdata.alert.core.model.impl.MutableDetectableEventType;
import org.joda.time.DateTime;

public class MockActionPlanFactory {

    static Random rnd = new Random();
    
    public static MutableActionPlan create() {
        MutableActionPlan mock = new MutableActionPlan();
        
        URI planUid = null;
        try {
            planUid = new URI("plan:" + rnd.nextLong());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        mock.setUid(planUid);
        mock.setDateCreated(new DateTime());
        DetectableEventType mockEventType = MockDetectableEventFactory.create();
        mock.setEventType(mockEventType);
        mock.setRuleSet(MockRuleSetFactory.createHeaderFilter(mockEventType, "text/xml"));
        
        return mock;
    }

}
