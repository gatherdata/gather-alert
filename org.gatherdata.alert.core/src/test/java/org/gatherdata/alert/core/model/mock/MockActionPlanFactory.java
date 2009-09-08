package org.gatherdata.alert.core.model.mock;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.MutableActionPlan;
import org.gatherdata.alert.core.model.MutableDetectableEventType;
import org.gatherdata.alert.core.model.RuleSet;
import org.joda.time.DateTime;

public class MockActionPlanFactory {

    static Random rnd = new Random();
    
    public static ActionPlan create() {
        MutableActionPlan mock = new MutableActionPlan();
        
        URI planUid = null;
        try {
            planUid = new URI("plan:" + rnd.nextLong());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        mock.setUid(planUid);
        mock.setName("mockPlan");
        mock.setDateCreated(new DateTime());
        mock.setDescription("a mocked-up ActionPlan");
        DetectableEventType mockEventType = MockDetectableEventFactory.create();
        mock.setEventType(mockEventType);
        mock.setRuleSet(MockRuleSetFactory.createHeaderFilter(mockEventType, "text/xml"));
        
        return mock;
    }

}
