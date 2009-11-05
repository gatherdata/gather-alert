/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
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

import static org.gatherdata.alert.builder.RuleSetBuilder.rules;
import static org.gatherdata.alert.builder.LanguageScriptBuilder.expressedIn;
import static org.gatherdata.alert.builder.PlannedNotificationBuilder.address;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.hamcrest.Matcher;

public class ActionPlanBuilderTest {


    @Test
    public void shouldBuildAnActionPlan() throws URISyntaxException {
        final String ACTION_PLAN_NAME = "test event";
        final String ACTION_PLAN_DESCRIPTION = "occurs only during tests";
        URI TEST_ADDRESS_URI = new URI("mailto:sysadmin@kollegger.name");
        final String MESSAGE_TEMPLATE_LANGUAGE = "vm";
        final String MESSAGE_TEMPLATE_SCRIPT = "hello world";
        
        ActionPlan builtTestPlan = ActionPlanBuilder.plan()
            .named(ACTION_PLAN_NAME)
            .describedAs(ACTION_PLAN_DESCRIPTION)
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
        
        assertThat(builtTestPlan.getName(), is(ACTION_PLAN_NAME));
        assertThat(builtTestPlan.getDescription(), is(ACTION_PLAN_DESCRIPTION));
        
        assertThat(builtTestPlan.getRuleSet(), notNullValue());
        RuleSet builtRuleSet = builtTestPlan.getRuleSet();
        assertThat(builtRuleSet.getPredicateCount(), is(equalTo(2)));
        assertThat(builtRuleSet.isActive(), is(true));
        assertThat(builtRuleSet.getPlan(), is(builtTestPlan));

        assertThat(builtTestPlan.getNotifications(), notNullValue());
        Iterable<PlannedNotification> builtNotifications = (Iterable<PlannedNotification>) builtTestPlan.getNotifications();
        PlannedNotification firstNotification = builtNotifications.iterator().next();
        assertThat(firstNotification.getDestination(), is(TEST_ADDRESS_URI));
        assertThat(firstNotification.getTemplate().getLanguage(), is(MESSAGE_TEMPLATE_LANGUAGE));
        assertThat(firstNotification.getTemplate().getScript(), is(MESSAGE_TEMPLATE_SCRIPT));
        assertThat(firstNotification.getPlan(), is(builtTestPlan));
    }

}
