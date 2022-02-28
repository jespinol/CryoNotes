package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.model.Screening;
import com.jmel.cryonotes.repository.ScreeningRepository;
import com.jmel.cryonotes.service.TemplateVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/screenings")
public class ScreeningsController extends AbstractController<Screening> {

    private static final String VIEW_NAME = "screenings";

    private static final String CLASS_NAME = "com.jmel.cryonotes.model.Screening";

    private static final Map<String, TemplateVariables> ATTRIBUTES = new LinkedHashMap<>();

    static {
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

        TemplateVariables sampleVariables = new TemplateVariables("Sample", true, true);
        sampleVariables.addHtmlAttribute("id", "sample");
        ATTRIBUTES.put("sample", sampleVariables);

        TemplateVariables microscopeVariables = new TemplateVariables("Microscope", true, false);
        ATTRIBUTES.put("microscope", microscopeVariables);

        TemplateVariables gridVariables = new TemplateVariables("Grid", true, false);
        ATTRIBUTES.put("grid", gridVariables);

        TemplateVariables resultVariables = new TemplateVariables("Main Result", true);
        ATTRIBUTES.put("result", resultVariables);

        TemplateVariables wasCollectedVariables = new TemplateVariables("Data Collection", false);
        ATTRIBUTES.put("wasCollected", wasCollectedVariables);

        TemplateVariables wasStoredVariables = new TemplateVariables("Storage", false);
        ATTRIBUTES.put("wasStored", wasStoredVariables);

        TemplateVariables commentsVariables = new TemplateVariables("Comments", false);
        ATTRIBUTES.put("comments", commentsVariables);
    }

    @Autowired
    ScreeningRepository repository;

    protected ScreeningsController() throws ClassNotFoundException {
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
    ScreeningRepository getRepository() {
        return repository;
    }

    @Override
    public List<Screening> getSearch(String keyword) {
        return repository.simpleSearch(keyword);
    }

    @GetMapping("/advanced_search/result")
    public String searchAdvanced(Model model, @RequestParam("date") String date, @RequestParam("name") String name, @RequestParam("category") String category, @RequestParam("creator") String creator, @RequestParam("microscope") String microscope, @RequestParam("grid") String grid, @RequestParam("result") String result, @RequestParam("wasCollected") String wasCollected, @RequestParam("wasStored") String wasStored, @RequestParam("comments") String comments) {
        List<Screening> searchResults = repository.advancedSearch(date, name, category, creator, microscope, grid, result, wasCollected, wasStored, comments);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("currentObject", getViewName());
        return "search_results";
    }
}