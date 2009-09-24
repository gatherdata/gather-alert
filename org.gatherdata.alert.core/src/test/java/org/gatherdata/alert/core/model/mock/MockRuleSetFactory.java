package org.gatherdata.alert.core.model.mock;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.model.impl.MutableLanguageScript;
import org.gatherdata.alert.core.model.impl.MutableRuleSet;

public class MockRuleSetFactory {

    static Random rnd = new Random();

    /**
     * Creates a mocked-up RuleSet which contains LanguageScripts
     * that check for a header named 'foo' being set to 'bar'.
     * 
     * @param forEvent
     * @param inDetectionContext
     * @return
     */
    public static MutableRuleSet createHeaderFilter(DetectableEventType forEvent, String inDetectionContext) {
        MutableRuleSet mock = new MutableRuleSet();
        
        mock.setActive(true);
        mock.setContext(inDetectionContext);
        mock.setIndicatedEventType(forEvent);
        mock.setSatisfyAll(true);
        try {
            mock.setUid(new URI("example:" + rnd.nextLong()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mock.add(new MutableLanguageScript("js", "/bar/.test(body)")); // weak javascript regex match for bar anywhere within angle brackets of body
        
        return mock;
    }

}
