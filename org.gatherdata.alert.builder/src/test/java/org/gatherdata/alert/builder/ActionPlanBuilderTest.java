package org.gatherdata.alert.builder;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;

import static org.gatherdata.alert.builder.EventTypeBuilder.event;
import static org.gatherdata.alert.builder.RuleSetBuilder.rules;
import static org.gatherdata.alert.builder.LanguageScriptBuilder.expressedIn;
import static org.gatherdata.alert.builder.PlannedNotificationBuilder.address;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.hamcrest.Matcher;

public class ActionPlanBuilderTest {


    @Test
    public void shouldBuildAnActionPlan() throws URISyntaxException {
        final String TEST_EVENT_NAME = "test event";
        final String TEST_EVENT_DESCRIPTION = "occurs only during tests";
        URI TEST_ADDRESS_URI = new URI("mailto:sysadmin@kollegger.name");
        final String MESSAGE_TEMPLATE_LANGUAGE = "vm";
        final String MESSAGE_TEMPLATE_SCRIPT = "hello world";
        
        ActionPlan builtTestPlan = ActionPlanBuilder.plan()
            .lookingFor(
                    event(TEST_EVENT_NAME)
                    .describedAs(TEST_EVENT_DESCRIPTION))
            .applyingRules(
                    rules("text/xml")
                    .rule(expressedIn("js").script("some javascript expression"))
                    .rule(expressedIn("xpath").script("some xpath expression"))
                    )
            .notifying(
                    address(TEST_ADDRESS_URI.toASCIIString()).message(expressedIn(MESSAGE_TEMPLATE_LANGUAGE).script(MESSAGE_TEMPLATE_SCRIPT))
                    )
            .build();
        
        assertThat(builtTestPlan, notNullValue());
        
        assertThat(builtTestPlan.getEventType(), notNullValue());
        DetectableEventType builtEventType = builtTestPlan.getEventType();
        assertThat(builtEventType.getName(), is(TEST_EVENT_NAME));
        assertThat(builtEventType.getDescription(), is(TEST_EVENT_DESCRIPTION));
        
        assertThat(builtTestPlan.getRuleSet(), notNullValue());
        RuleSet builtRuleSet = builtTestPlan.getRuleSet();
        assertThat(builtRuleSet.getPredicateCount(), is(equalTo(2)));
        assertThat(builtRuleSet.getIndicatedEventType(), is(builtEventType));
        assertThat(builtRuleSet.isActive(), is(true));

        assertThat(builtTestPlan.getNotifications(), notNullValue());
        Iterable<PlannedNotification> builtNotifications = (Iterable<PlannedNotification>) builtTestPlan.getNotifications();
        PlannedNotification firstNotification = builtNotifications.iterator().next();
        assertThat(firstNotification.getDestination(), is(TEST_ADDRESS_URI));
        assertThat(firstNotification.getTemplate().getLanguage(), is(MESSAGE_TEMPLATE_LANGUAGE));
        assertThat(firstNotification.getTemplate().getScript(), is(MESSAGE_TEMPLATE_SCRIPT));
        
    }

}
