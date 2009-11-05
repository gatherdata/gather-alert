/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model.impl;

import java.net.URI;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.commons.model.DescribedEntity;
import org.gatherdata.commons.model.impl.MutableDescribedEntity;

public class MutablePlannedNotification extends MutableDescribedEntity implements PlannedNotification {

    /**
     * Auto-generated
     */
    private static final long serialVersionUID = 4371753026929595918L;
    
    private URI destination;
    private LanguageScript template;

    private ActionPlan plan;

    public ActionPlan getPlan() {
        return plan;
    }
    
    public void setPlan(ActionPlan plan) {
        this.plan = plan;
    }

    public URI getDestination() {
        return this.destination;
    }
    
    public void setDestination(URI destination) {
        this.destination = destination;
    }

    public LanguageScript getTemplate() {
        return this.template;
    }

    public void setTemplate(LanguageScript template) {
        this.template = template;
    }

    public PlannedNotification copy(PlannedNotification template) {
        if (template != null) {
            super.copy(template);
            setDestination(template.getDestination());
            LanguageScript templateTemplate = template.getTemplate();
            if (templateTemplate != null) {
                setTemplate(new MutableLanguageScript().copy(templateTemplate));
            } else {
                setTemplate(null);
            }
        }
        return this;
    }

    public PlannedNotification update(PlannedNotification template) {
        if (template != null) {
            super.update(template);
            if (template.getDestination() != null) {
                setDestination(template.getDestination());
            }
            LanguageScript templateTemplate = template.getTemplate();
            if (templateTemplate != null) {
                MutableLanguageScript currentTemplate = new MutableLanguageScript();
                currentTemplate.copy(getTemplate());
                currentTemplate.update(templateTemplate);
                setTemplate(currentTemplate);
            }
        }
        return this;
    }
    
}
