package org.gatherdata.alert.core.model;

import java.net.URI;

import org.gatherdata.commons.model.MutableDescribedEntity;

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
}
