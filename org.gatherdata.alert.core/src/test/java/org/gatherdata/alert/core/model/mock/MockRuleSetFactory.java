package org.gatherdata.alert.core.model.mock;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

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
    public static MutableRuleSet createHeaderFilter(String inDetectionContext) {
        MutableRuleSet mock = new MutableRuleSet();
        
        mock.setActive(true);
        mock.setContext(inDetectionContext);
        mock.setSatisfyAll(true);
        try {
            mock.setUid(new URI("example:" + rnd.nextLong()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        MutableLanguageScript mockPredicate = new MutableLanguageScript("js", "/bar/.test(body)");
        mockPredicate.selfIdentify();
        mock.add(mockPredicate);
        mock.selfIdentify();
        return mock;
    }

}
