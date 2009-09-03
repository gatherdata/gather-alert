package org.gatherdata.alert.core.model.mock;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.MutableDetectableEventType;

public class MockDetectableEventFactory {

    static Random rnd = new Random();
    
    public static DetectableEventType create() {
        MutableDetectableEventType mock = new MutableDetectableEventType();
        URI eventUid = null;
        try {
            eventUid = new URI("mockEvent:" + rnd.nextLong());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mock.setUid(eventUid);
        mock.setName("mockEvent");
        mock.setDescription("a mocked-up detectable event type");
        
        return mock;
    }

    
    
}