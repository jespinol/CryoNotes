package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.model.Sample;
import com.jmel.cryonotes.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    private static final Map<String, String> ALL_ATTRIBUTES = new LinkedHashMap<>();

    static {
        ALL_ATTRIBUTES.put("Date", "date");
        ALL_ATTRIBUTES.put("Sample name", "name");
        ALL_ATTRIBUTES.put("Category", "category");
        ALL_ATTRIBUTES.put("Created by", "creator");
        ALL_ATTRIBUTES.put("M.W. (kDa)", "molecularWeight");
        ALL_ATTRIBUTES.put("Is complex", "isComplex");
        ALL_ATTRIBUTES.put("Stoichiometry", "stoichiometry");
        ALL_ATTRIBUTES.put("Comments", "comments");
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

    Map<String, String> getSummaryAttributes() {
        return SUMMARY_ATTRIBUTES;
    }

    Map<String, String> getAllAttributes() {
        return ALL_ATTRIBUTES;
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