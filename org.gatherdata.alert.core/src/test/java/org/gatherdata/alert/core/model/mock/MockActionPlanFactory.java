/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model.mock;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.model.impl.MutableActionPlan;
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
        mock.setRuleSet(MockRuleSetFactory.createHeaderFilter("text/xml"));
        
        mock.add(MockPlannedNotificationFactory.create());
        
        return mock;
    }

}
