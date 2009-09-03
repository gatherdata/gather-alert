package org.gatherdata.alert.core.model.mock;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.MutableLanguageScript;
import org.gatherdata.alert.core.model.MutableRuleSet;
import org.gatherdata.alert.core.model.RuleSet;

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
        mock.setDescription("an example (hard-coded) rule-set");
        mock.setIndicatedEventType(forEvent);
        mock.setName("example");
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
