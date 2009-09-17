package org.gatherdata.alert.dao.db4o.model;

import java.net.URI;

import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.commons.db.db4o.model.DescribedEntityDb4o;
import org.joda.time.DateTime;

public class LanguageScriptDb4o extends DescribedEntityDb4o implements LanguageScript {

    private String language;
    private String script;

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
    
    public LanguageScriptDb4o copy(LanguageScript template) {
        if ((template != null) && (template != this)) {
            super.copy(template);
            setLanguage(template.getLanguage());
            setScript(template.getScript());
        }
        return this;
    }

}
