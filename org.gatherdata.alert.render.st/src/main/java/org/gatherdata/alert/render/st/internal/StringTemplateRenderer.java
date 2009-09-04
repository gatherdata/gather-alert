package org.gatherdata.alert.render.st.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.gatherdata.alert.core.spi.TemplateRenderer;
import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;

public class StringTemplateRenderer implements TemplateRenderer {
    
    public static final Collection<String> TEMPLATE_TYPES = Arrays.asList("st");

    public Iterable<String> getTemplateTypes() {
        return TEMPLATE_TYPES;
    }

    public boolean canRender(String templateType) {
        return TEMPLATE_TYPES.contains(templateType);
    }

    public String render(String template, Map<String, Object> attributes) {
        StringTemplate hello = new StringTemplate("Hello, $name$", DefaultTemplateLexer.class);
        hello.setAttribute("name", "World");
        return (hello.toString());
    }


}
