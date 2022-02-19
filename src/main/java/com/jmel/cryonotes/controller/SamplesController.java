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

    private static final Map<String, String> SUMMARY_ATTRIBUTES = new LinkedHashMap<>();

    static {
        SUMMARY_ATTRIBUTES.put("Date", "date");
        SUMMARY_ATTRIBUTES.put("Sample name", "name");
        SUMMARY_ATTRIBUTES.put("Category", "category");
        SUMMARY_ATTRIBUTES.put("Created by", "creator");
        SUMMARY_ATTRIBUTES.put("M.W. (kDa)", "molecularWeight");
        SUMMARY_ATTRIBUTES.put("Is complex", "isComplex");
        SUMMARY_ATTRIBUTES.put("Stoichiometry", "stoichiometry");
    }

    private static final Map<String, String[]> ATTRIBUTES = new LinkedHashMap<>();

    static {
        //key= label for fields
        // values = [0]: attribute name, [1] attribute type, ...
        ATTRIBUTES.put("Date", new String[]{"date"});
        ATTRIBUTES.put("Sample name", new String[]{"name"});
        ATTRIBUTES.put("Category", new String[]{"category"});
        ATTRIBUTES.put("Created by", new String[]{"creator"});
        ATTRIBUTES.put("M.W. (kDa)", new String[]{"molecularWeight"});
        ATTRIBUTES.put("Is complex", new String[]{"isComplex"});
        ATTRIBUTES.put("Stoichiometry", new String[]{"stoichiometry"});
        ATTRIBUTES.put("Comments", new String[]{"comments"});
    }

//    private static final Map<String, TemplateVariables> ATTRIBUTES = new LinkedHashMap<>();
//
//    class TemplateVariables {
//        String label;
//        boolean summaryDisplay;
//        Map<String, String> htmlAttributes = new HashMap<>();
//
//        public TemplateVariables(String label, boolean summaryDisplay) {
//            this.label = label;
//            this.summaryDisplay = summaryDisplay;
//        }
//
//        public TemplateVariables(String label) {
//            this.label = label;
//            this.summaryDisplay = false;
//        }
//
//        void addHtmlAttribute(String key, String value) {
//            htmlAttributes.put(key, value);
//        }
//
//        @Override
//        public String toString() {
//            String output = "";
//            for (String key : htmlAttributes.keySet()) {
//                output += key + "=" + htmlAttributes.get(key) + ",";
//            }
//            return output.substring(0, output.length() - 1);
//        }
//    }
//
//    {
//        TemplateVariables dateVariables = new TemplateVariables("Date", true);
//        dateVariables.addHtmlAttribute("type", "date");
//        dateVariables.addHtmlAttribute("id", "date");
//        ATTRIBUTES.put("date", dateVariables);
//
//        TemplateVariables nameVariables = new TemplateVariables("Name", true);
//        ATTRIBUTES.put("name", nameVariables);
//
//        TemplateVariables categoryVariables = new TemplateVariables("Category", true);
//        ATTRIBUTES.put("category", categoryVariables);
//
//        TemplateVariables creatorVariables = new TemplateVariables("Created by", true);
//        creatorVariables.addHtmlAttribute("id", "creator");
//        ATTRIBUTES.put("creator", creatorVariables);
//
//        TemplateVariables weightVariables = new TemplateVariables("M.W. (kDa)", false);
//        weightVariables.addHtmlAttribute("type", "number");
//        weightVariables.addHtmlAttribute("min", "1");
//        ATTRIBUTES.put("molecularWeight", weightVariables);
//
//        TemplateVariables complexVariables = new TemplateVariables("Is complex", false);
//        ATTRIBUTES.put("isComplex", complexVariables);
//
//        TemplateVariables stoichiometryVariables = new TemplateVariables("Stoichiometry", false);
//        ATTRIBUTES.put("stoichiometry", stoichiometryVariables);
//
//        TemplateVariables commentsVariables = new TemplateVariables("Comments", false);
//        commentsVariables.addHtmlAttribute("type", "textarea");
//        ATTRIBUTES.put("comments", commentsVariables);
//    }

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

    Map<String, String> getSummaryAttributes() {
        return SUMMARY_ATTRIBUTES;
    }

    Map<String, String[]> getAllAttributes() {
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