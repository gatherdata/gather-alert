/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.spi;

import java.net.URI;

import org.gatherdata.alert.core.model.Notification;
import org.gatherdata.alert.core.model.SentNotice;

/**
 * A Notifier sends a Notification, producing a SentNotice.
 *
 */
public interface Notifier {

    public Iterable<String> getSchemeTypes();

    public boolean canSendTo(URI notificationAddress);

	public void notify(Notification notice);
	
}
