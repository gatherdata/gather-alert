package org.gatherdata.alert.detect.bsf.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gatherdata.alert.core.model.DetectedEvent;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.MutableDetectedEvent;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.spi.EventDetector;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

public class BsfEventDetector implements EventDetector {
    private static Log log = LogFactory.getLog(BsfEventDetector.class);

    private ScriptEngineManager scriptEngineManager;

    public BsfEventDetector() {
        this.scriptEngineManager = new ScriptEngineManager();
        List<ScriptEngineFactory> engineFactories = scriptEngineManager.getEngineFactories();
        log.info("Available ScriptEngineFactories...");
        for (ScriptEngineFactory factory : engineFactories) {
            log.info("\t" + factory.getEngineName() + " - " + factory.getLanguageName() + " " + "ext:"
                    + factory.getExtensions() + " " + "names:" + factory.getNames());
        }
    }

    public Iterable<DetectedEvent> detect(Iterable<RuleSet> usingRules, Map<String, Object> attributes) {
        Set<DetectedEvent> detectedEvents = new HashSet<DetectedEvent>();

        DateTime detectionTime = new DateTime();

        for (RuleSet rule : usingRules) {
            if (rule.isActive()) {
                boolean anyMatch = false;
                boolean allMatch = true;

                for (LanguageScript predicate : rule.getPredicates()) {
                    ScriptEngine engine = scriptEngineManager.getEngineByName(predicate.getLanguage());
                    if (engine != null) {
                        try {
                            Boolean doesMatch = (Boolean) engine.eval(predicate.getScript(),
                                    adaptToScriptContext(attributes));
                            anyMatch |= doesMatch;
                            allMatch &= doesMatch;

                        } catch (ScriptException e) {
                            e.printStackTrace();
                        }
                    } else {
                        log.warn("Cant't evaluate predicate for missing language: " + predicate.getLanguage());
                    }
                }

                if ((!rule.isSatisfyAll() && anyMatch) || allMatch) {
                    detectedEvents.add(MutableDetectedEvent.createFor(detectionTime, CbidFactory.createCbid(attributes
                            .get("body").toString()), rule));
                }
            }
        }
        return detectedEvents;
    }

    private Bindings adaptToScriptContext(Map<String, Object> attributes) {
        Bindings scriptBindings = new SimpleBindings();
        scriptBindings.putAll(attributes);
        return scriptBindings;
    }

}
