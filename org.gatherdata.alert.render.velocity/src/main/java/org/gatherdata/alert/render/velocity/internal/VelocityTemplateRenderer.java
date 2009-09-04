package org.gatherdata.alert.render.velocity.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.gatherdata.alert.core.spi.TemplateRenderer;

public class VelocityTemplateRenderer implements TemplateRenderer {

    public static final Collection<String> TEMPLATE_TYPES = Arrays.asList("vm");

    boolean velocityIsReady = false;

    private final String logTag = "velocity";

    public VelocityTemplateRenderer() {
        try {
            Velocity.init();
            velocityIsReady = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Iterable<String> getTemplateTypes() {
        return TEMPLATE_TYPES;
    }

    public boolean canRender(String templateType) {
        return velocityIsReady && TEMPLATE_TYPES.contains(templateType);
    }

    public String render(String template, Map<String, Object> attributes) {
        StringWriter writer = new StringWriter();
        VelocityContext context = new VelocityContext();
        context.put("name", "World");
        if (attributes != null) {
            for (String key : attributes.keySet()) {
                context.put(key, attributes.get(key));
            }
        }
        try {
            Velocity.evaluate(context, writer, logTag, template);
        } catch (ParseErrorException e) {
            e.printStackTrace();
        } catch (MethodInvocationException e) {
            e.printStackTrace();
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (writer.toString());
    }

}
