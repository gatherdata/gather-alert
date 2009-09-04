package org.gatherdata.alert.core.spi;

import java.util.Map;

public interface TemplateRenderer {

    public Iterable<String> getTemplateTypes();
    
    public String render(String template, Map<String, Object> attributes);

    public boolean canRender(String templateType);
    
}
