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
