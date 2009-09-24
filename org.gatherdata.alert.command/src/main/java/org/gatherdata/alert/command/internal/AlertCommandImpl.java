package org.gatherdata.alert.command.internal;

import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.felix.shell.Command;
import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectedEvent;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.Notification;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.model.impl.ActionPlanFormatter;
import org.gatherdata.alert.core.model.impl.MutableNotification;
import org.gatherdata.alert.core.model.impl.PlannedNotificationFormatter;
import org.gatherdata.alert.core.model.impl.RuleSetFormatter;
import org.gatherdata.alert.core.spi.AlertService;
import org.gatherdata.alert.core.spi.EventDetector;
import org.gatherdata.alert.core.spi.Notifier;
import org.gatherdata.alert.core.spi.TemplateRenderer;

import com.google.inject.Inject;

public class AlertCommandImpl implements Command {

    public static final String COMMAND_NAME = "alert";

    private final Pattern commandPattern = Pattern.compile("^(\\w+)\\s*(\\w+)\\s*(.*)");

    private final Pattern subCommandPattern = Pattern.compile("^(\\S+)\\s+(.*)");

    @Inject
    AlertService alertService;
    
    @Inject
    EventDetector eventDetector;
    
    @Inject
    Iterable<TemplateRenderer> renderers;
    
    @Inject
    Iterable<Notifier> notifiers;

    public void execute(String argString, PrintStream out, PrintStream err) {
        Matcher argMatcher = commandPattern.matcher(argString);
        if (argMatcher.matches()) {
            String subCommand = argMatcher.group(2);
            String subArguments = argMatcher.group(3);

            if (alertService == null) {
                err.println("AlertService not available");
                return;
            }

            if ("help".equals(subCommand)) {
                out.println("subcommands: list, detect, render");
                out.println("\tlist [category] - show alert action plans [or other categories]");
                out.println("\tdetect <rule context> <sample data> - apply rules to sample data");
                out.println("\trender <template type> <template body> - render body");
            } else if ("detect".equals(subCommand)) {
                Matcher filterArguments = subCommandPattern.matcher(subArguments);
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
                String category = subArguments.trim();
                if ("notifiers".equals(category)) {
                    for (Notifier notifier : notifiers) {
                        out.println(notifier.toString() + " can handle these destination schemes...");
                        out.print("\t");
                        for (String scheme : notifier.getSchemeTypes()) {
                            out.print(scheme + " ");
                        }
                        out.println();
                    }
                } else if ("renderers".equals(category)) {
                    for (TemplateRenderer renderer : renderers) {
                        out.println(renderer.toString() + " can render templates of type...");
                        out.print("\t");
                        for (String type : renderer.getTemplateTypes()) {
                            out.print(type + " ");
                        }
                        out.println();
                    }
                } else {
                    Iterable<ActionPlan> plans = (Iterable<ActionPlan>) alertService.getAll();
                    if (plans != null) {
                        for (ActionPlan plan : alertService.getAll()) {
                            out.println(ActionPlanFormatter.toString(plan));
                            RuleSet planRules = plan.getRuleSet();
                            if (planRules != null) {
                                out.println(RuleSetFormatter.toLongString(planRules));
                            } else {
                                out.println("\tplan has no defined RuleSet");
                            }
                            out.println("notification plans...");
                            for (PlannedNotification notification : plan.getNotifications()) {
                                out.println("\t" + PlannedNotificationFormatter.toLongString(notification));
                            }
                        }
                    } else {
                        err.println("AlertService.getAll() returned null. Current AlertServiceDao is probably broken.");
                    }
                }
            } else if ("render".equals(subCommand)) { 
                Matcher filterArguments = subCommandPattern.matcher(subArguments);
                if (filterArguments.matches()) {
                    String templateType = filterArguments.group(1);
                    for (TemplateRenderer renderer : renderers) {
                        if (renderer.canRender(templateType)) {
                            String template = filterArguments.group(2);
                            out.println(renderer.render(template, null));
                        }
                    }
                }
            } else if ("send".equals(subCommand)) {
                Matcher sendArguments = subCommandPattern.matcher(subArguments);
                if (sendArguments.matches()) {
                    String destination = sendArguments.group(1);
                    String message = sendArguments.group(2);
                    URI destinationURI = null;
                    try {
                        destinationURI = new URI(destination);
                    } catch (URISyntaxException e) {
                        err.println("bad destination, because: " + e.toString());
                    }
                    
                    for (Notifier notifier : notifiers) {
                        if (notifier.canSendTo(destinationURI)) {
                            MutableNotification notice = new MutableNotification();
                            notice.setDestination(destinationURI);
                            notice.setMessage(message);
                            notifier.notify(notice);
                        }
                    }
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
