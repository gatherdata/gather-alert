package org.gatherdata.alert.builder;

import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.MutableLanguageScript;

public class LanguageScriptBuilder {
    
    private final MutableLanguageScript languageScript = new MutableLanguageScript();
    
    private LanguageScriptBuilder() {
        ;
    }

    public static LanguageScriptBuilder expressedIn(String scriptLanguage) {
        return new LanguageScriptBuilder().language(scriptLanguage);
    }

    public LanguageScriptBuilder language(String scriptLanguage) {
        languageScript.setLanguage(scriptLanguage);
        return this;
    }

    public LanguageScript build() {
        return languageScript;
    }

    public LanguageScriptBuilder script(String script) {
        languageScript.setScript(script);
        return this;
    }
}
