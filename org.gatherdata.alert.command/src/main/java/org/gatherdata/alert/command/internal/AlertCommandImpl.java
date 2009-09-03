package org.gatherdata.alert.command.internal;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.felix.shell.Command;
import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectedEvent;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.spi.AlertService;
import org.gatherdata.alert.core.spi.EventDetector;

import com.google.inject.Inject;

public class AlertCommandImpl implements Command {

    public static final String COMMAND_NAME = "alert";

    private final Pattern commandPattern = Pattern.compile("^(\\w+)\\s*(\\w+)\\s*(.*)");

    private final Pattern detectCommandPattern = Pattern.compile("^(\\S+)\\s+(.*)");

    @Inject
    AlertService alertService;
    
    @Inject
    EventDetector eventDetector;

    public void execute(String argString, PrintStream out, PrintStream err) {
        Matcher argMatcher = commandPattern.matcher(argString);
        if (argMatcher.matches()) {
            String subCommand = argMatcher.group(2);
            String subArguments = argMatcher.group(3);

            if (alertService == null) {
                err.println("AlertService not available");
                return;
            }

            if ("detect".equals(subCommand)) {
                Matcher filterArguments = detectCommandPattern.matcher(subArguments);
                if (filterArguments.matches()) {
                    String rulesetContext = filterArguments.group(1);
                    Iterable<RuleSet> rules = alertService.getActiveRulesetsFor(rulesetContext);
                    if (rules != null) {
                        if (eventDetector != null) {
                            String toData = filterArguments.group(2);
                            Map<String, Object> scriptMap = new HashMap<String, Object>();
                            scriptMap.put("body", toData);
                            Iterable<DetectedEvent> events = eventDetector.detect(rules, scriptMap);
                            boolean eventsWereDetected = false;
                            for (DetectedEvent dev : events) {
                            	eventsWereDetected = true;
                                out.println("\t" + dev);
                            }
                            if (!eventsWereDetected) out.println("That data was nothing to get excited about.");
                        } else {
                            err.println("Got rules, but no EventDetector to know what to do with them.");
                        }
                    } else {
                        err.println("Apparently no rulesets apply to the \"" + rulesetContext + "\" context.");
                    }
                } else {
                    err.println("Detect what with who, now? I'm confused by: " + subArguments);
                }
            } else if ("list".equals(subCommand)) {
                Iterable<ActionPlan> plans = alertService.getAll();
                if (plans != null) {
                    for (ActionPlan plan : alertService.getAll()) {
                        out.println(plan);
                    }
                } else {
                    err.println("AlertService.getAll() returned null. Current AlertServiceDao is probably broken.");    
                }
            } else {
                err.println("sorry, '" + subCommand + "' is not a recognized sub-command");
            }
        } else {
            err.println("sorry, don't know what to do with: " + argString);
        }

    }

    public String getName() {
        return COMMAND_NAME;
    }

    public String getShortDescription() {
        return "interacts with the AlertService";
    }

    public String getUsage() {
        return "alert <sub-command>";
    }

}