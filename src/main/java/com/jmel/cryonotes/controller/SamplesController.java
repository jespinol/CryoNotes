package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.model.Sample;
import com.jmel.cryonotes.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/samples")
public class SamplesController extends AbstractController<Sample> {

    private static final String VIEW_NAME = "samples";

    private static final String CLASS_NAME = "com.jmel.cryonotes.model.Sample";

    private static final Map<String, TemplateVariables> ATTRIBUTES = new LinkedHashMap<>();

    class TemplateVariables {
        String label;
        boolean inSummary;
        Map<String, String> htmlAttributes = new HashMap<>();

        public TemplateVariables(String label, boolean inSummary) {
            this.label = label;
            this.inSummary = inSummary;
        }

        void addHtmlAttribute(String key, String value) {
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
    }

    {
        TemplateVariables dateVariables = new TemplateVariables("Date", true);
        dateVariables.addHtmlAttribute("type", "date");
        dateVariables.addHtmlAttribute("id", "dateForm");
        ATTRIBUTES.put("date", dateVariables);

        TemplateVariables nameVariables = new TemplateVariables("Name", true);
        ATTRIBUTES.put("name", nameVariables);

        TemplateVariables categoryVariables = new TemplateVariables("Category", true);
        ATTRIBUTES.put("category", categoryVariables);

        TemplateVariables creatorVariables = new TemplateVariables("Created by", true);
        creatorVariables.addHtmlAttribute("id", "creatorForm");
        ATTRIBUTES.put("creator", creatorVariables);

        TemplateVariables weightVariables = new TemplateVariables("M.W. (kDa)", false);
        weightVariables.addHtmlAttribute("type", "number");
        weightVariables.addHtmlAttribute("min", "0");
        ATTRIBUTES.put("molecularWeight", weightVariables);

        TemplateVariables complexVariables = new TemplateVariables("Is complex", false);
        ATTRIBUTES.put("isComplex", complexVariables);

        TemplateVariables stoichiometryVariables = new TemplateVariables("Stoichiometry", false);
        ATTRIBUTES.put("stoichiometry", stoichiometryVariables);

        TemplateVariables commentsVariables = new TemplateVariables("Comments", false);
        ATTRIBUTES.put("comments", commentsVariables);
    }

    @Autowired
    SampleRepository repository;

    protected SamplesController() throws ClassNotFoundException {
    }

    String getViewName() {
        return VIEW_NAME;
    }

    String getClassName() {
        return CLASS_NAME;
    }

    Map<String, TemplateVariables> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public SampleRepository getRepository() {
        return repository;
    }

    @Override
    public List<Sample> getSearch(String keyword) {
        return repository.simpleSearch(keyword);
    }

    @Override
    public List<Sample> getAdvancedSearch(String date, String name, String category, String creator, int molecularWeight, String iscComplex, String stoichiometry, String comments) {
        return repository.advancedSearch(date, name, category, creator, molecularWeight, iscComplex, stoichiometry, comments);
    }
}