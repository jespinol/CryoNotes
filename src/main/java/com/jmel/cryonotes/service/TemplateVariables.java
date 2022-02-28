package com.jmel.cryonotes.service;

import java.util.HashMap;
import java.util.Map;

public class TemplateVariables {
    String label;
    boolean inSummary;
    boolean isDropdown;
    Map<String, String> htmlAttributes = new HashMap<>();

    public TemplateVariables(String label, boolean inSummary, boolean isDropdown) {
        this.label = label;
        this.inSummary = inSummary;
        this.isDropdown = isDropdown;
    }

    public TemplateVariables(String label, boolean inSummary) {
        this.label = label;
        this.inSummary = inSummary;
        this.isDropdown = false;
    }

    public void addHtmlAttribute(String key, String value) {
        htmlAttributes.put(key, value);
    }

    public String getHtmlAttributes() {
        String output = "blank=1";
        for (String key : htmlAttributes.keySet()) {
            output += "," + key + "=" + htmlAttributes.get(key);
        }
        return output;
    }

    public String getLabel() {
        return label;
    }

    public boolean isInSummary() {
        return inSummary;
    }

    public boolean isDropdown() {
        return isDropdown;
    }
}
