package org.gatherdata.alert.core.model.mock;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.model.impl.MutableLanguageScript;
import org.gatherdata.alert.core.model.impl.MutablePlannedNotification;
import org.gatherdata.alert.core.model.impl.MutableRuleSet;
import org.joda.time.DateTime;

public class MockPlannedNotificationFactory {

    static Random rnd = new Random();

    public static MutablePlannedNotification create() {
        MutablePlannedNotification mock = new MutablePlannedNotification();
        
        mock.setDateCreated(new DateTime());
        mock.setName("mockPlan" + rnd.nextInt());
        mock.setDescription("mocked up notification plan" + rnd.nextInt());
        mock.setTemplate(new MutableLanguageScript("vm", "this is a mock message"));
        try {
            mock.setDestination(new URI("mailto:mock@mocking.org"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mock.selfIdentify();
        
        return mock;
    }

}
