/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model.impl;

import java.net.URI;

import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.commons.model.DescribedEntity;
import org.gatherdata.commons.model.impl.MutableDescribedEntity;

public class MutableLanguageScript extends MutableDescribedEntity  implements LanguageScript {

    /**
     * Auto-generated.
     */
    private static final long serialVersionUID = 3525073677055282821L;
    
    private String language;
    private String script;

    public MutableLanguageScript() {
        ;
    }
    
    public MutableLanguageScript(String language, String script) {
        this.language = language;
        this.script = script;
    }

    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getScript() {
        return this.script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public LanguageScript copy(LanguageScript template) {
        if (template != null) {
            super.copy(template);
            setLanguage(template.getLanguage());
            setScript(template.getScript());
        }
        return this;
    }

    public LanguageScript update(LanguageScript template) {
        if (template != null) {
            super.update(template);
            String templateLanguage = template.getLanguage();
            if (templateLanguage != null) {
                setLanguage(templateLanguage);
            }
            String templateScript = template.getScript();
            if (templateScript != null) {
                setScript(templateScript);
            }
        }
        return this;
    }    
    
}
