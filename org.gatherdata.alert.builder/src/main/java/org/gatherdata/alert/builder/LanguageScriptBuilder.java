/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.builder;

import java.util.Random;

import org.apache.commons.lang.NullArgumentException;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.impl.MutableLanguageScript;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

public class LanguageScriptBuilder {
    
    private final MutableLanguageScript languageScript = new MutableLanguageScript();
    
    private Random rnd = new Random();
    
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

    public MutableLanguageScript build() {
        if (languageScript.getLanguage() == null) {
            throw new NullArgumentException("language");
        }
        if (languageScript.getScript() == null) {
            throw new NullArgumentException("script");
        }
        if (languageScript.getDateCreated() == null) {
            languageScript.setDateCreated(new DateTime());
        }
        if (languageScript.getName() == null) {
            languageScript.setName("script" + rnd.nextInt());
        }
        if (languageScript.getDescription() == null) {
            languageScript.setDescription("undescribed");
        }
        if (languageScript.getUid() == null) {
            languageScript.setUid(CbidFactory.createCbid(
                    languageScript.getLanguage() + languageScript.getScript()
                    ));
        }
        return languageScript;
    }

    public LanguageScriptBuilder script(String script) {
        languageScript.setScript(script);
        return this;
    }
}
