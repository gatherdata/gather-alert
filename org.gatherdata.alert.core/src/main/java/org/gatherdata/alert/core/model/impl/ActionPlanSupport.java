/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.commons.model.impl.DescribedEntitySupport;
import org.gatherdata.commons.model.impl.UniqueEntitySupport;

public class ActionPlanSupport extends DescribedEntitySupport {

	public boolean equals(ActionPlan lhs, ActionPlan rhs) {
		boolean areEqual = super.equals(lhs, rhs);

		return areEqual;
	}

	public static boolean deepEquals(ActionPlan lhs, ActionPlan rhs) {
		boolean areEqual = true;

		if (lhs != rhs) { // don't bother comparing object to itself
			areEqual = UniqueEntitySupport.deepEquals(lhs, rhs);

			if (areEqual) { // check inner members
				Map<URI, PlannedNotification> lhsNotificationMap = new HashMap<URI, PlannedNotification>();
				for (PlannedNotification notification : lhs
						.getNotifications()) {
					lhsNotificationMap.put(notification.getUid(),
							notification);
				}
				Map<URI, PlannedNotification> rhsNotificationMap = new HashMap<URI, PlannedNotification>();
				for (PlannedNotification notification : rhs
						.getNotifications()) {
					rhsNotificationMap.put(notification.getUid(),
							notification);
				}
				areEqual = lhsNotificationMap.size() == rhsNotificationMap
						.size();
				if (areEqual) { // check RuleSets
					for (URI key : lhsNotificationMap.keySet()) {
						PlannedNotification lhsNotification = lhsNotificationMap
								.get(key);
						PlannedNotification rhsNotification = rhsNotificationMap
								.get(key);

						areEqual = PlannedNotificationSupport.deepEquals(
								lhsNotification, rhsNotification);

					}

					if (areEqual) { // check RuleSets
						areEqual = RuleSetSupport.deepEquals(lhs
								.getRuleSet(), rhs.getRuleSet());
					}
				}
			}
		}

		return areEqual;
	}
}
