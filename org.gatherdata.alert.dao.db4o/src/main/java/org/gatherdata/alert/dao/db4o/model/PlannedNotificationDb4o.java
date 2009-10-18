package org.gatherdata.alert.dao.db4o.model;

import java.net.URI;
import java.net.URISyntaxException;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.commons.db.db4o.model.UniqueEntityDb4o;
import org.joda.time.DateTime;

public class PlannedNotificationDb4o extends UniqueEntityDb4o implements PlannedNotification {

    private transient URI lazyDestination;
    private String destinationAsAscii;
    private LanguageScriptDb4o template;
    private ActionPlanDb4o plan;

    public URI getDestination() {
        if ((this.lazyDestination == null) && (destinationAsAscii != null)) {
            try {
                lazyDestination = new URI(destinationAsAscii);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return this.lazyDestination;
    }
    
    public void setDestination(String destinationAsAscii) {
        this.destinationAsAscii = destinationAsAscii;
    }

    public LanguageScript getTemplate() {
        return this.template;
    }
    
    public void setTemplate(LanguageScriptDb4o template) {
        this.template = template;
    }

    public PlannedNotificationDb4o copy(PlannedNotification template) {
        if ((template != null) && (template != this)) {
            super.copy(template);
            URI templateDestination = template.getDestination();
            if (templateDestination != null) {
                setDestination(templateDestination.toASCIIString());
            }
            LanguageScript templateTemplate = template.getTemplate();
            if (templateTemplate != null) {
                setTemplate(new LanguageScriptDb4o().copy(templateTemplate));
            }
        }
        return this;
    }

    public ActionPlan getPlan() {
        return plan;
    }
    
    public void setPlan(ActionPlanDb4o plan) {
        this.plan = plan;
    }

}
