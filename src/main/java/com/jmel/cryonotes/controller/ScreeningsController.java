package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.model.Screening;
import com.jmel.cryonotes.repository.ScreeningRepository;
import com.jmel.cryonotes.service.TemplateVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        TemplateVariables nameVariables = new TemplateVariables("Short description", true);
        ATTRIBUTES.put("name", nameVariables);

        TemplateVariables categoryVariables = new TemplateVariables("Category", true);
        ATTRIBUTES.put("category", categoryVariables);

        TemplateVariables creatorVariables = new TemplateVariables("Created by", true);
        creatorVariables.addHtmlAttribute("id", "creatorForm");
        ATTRIBUTES.put("creator", creatorVariables);

        TemplateVariables sampleVariables = new TemplateVariables("Sample", true, true);
        sampleVariables.addHtmlAttribute("id", "sample");
        ATTRIBUTES.put("sample", sampleVariables);

        TemplateVariables microscopeVariables = new TemplateVariables("Microscope", true, true);
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
    public Page<Screening> getSearch(String keyword, Pageable pageable) {
        return repository.simpleSearch(keyword, pageable);
    }

    @GetMapping("/advanced_search/result")
    public String searchAdvanced(Model model, @RequestParam("date") String date, @RequestParam("name") String name, @RequestParam("category") String category, @RequestParam("creator") String creator, @RequestParam(value = "sample", defaultValue = "-1") int sample, @RequestParam(value = "microscope", defaultValue = "-1") int microscope, @RequestParam("grid") String grid, @RequestParam("result") String result, @RequestParam("wasCollected") String wasCollected, @RequestParam("wasStored") String wasStored, @RequestParam("comments") String comments, @RequestParam(defaultValue = "0") Integer pageNo,
                                 @RequestParam(defaultValue = "20") Integer pageSize,
                                 @RequestParam(defaultValue = "id") String sortBy,
                                 @RequestParam(defaultValue = "false") String ascending) {
        Pageable pageRequest = getPaging(pageNo, pageSize, sortBy, ascending);
        Page<Screening> searchResults = repository.advancedSearch(pageRequest, date, name, category, creator, grid, result, wasCollected, wasStored, comments);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("currentObject", getViewName());
        model.addAttribute("viewType", "advanced_search/result?" + "date=" + date + "&" + "name=" + name + "&" + "category=" + category + "&" + "creator=" + creator + "&" + "grid=" + grid + "&" + "result=" + result + "&" + "wasCollected=" + wasCollected + "&" + "wasStored=" + wasStored + "&" + "comments=" + comments + "&");
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", searchResults.getTotalPages());
        model.addAttribute("ascending", ascending);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("totalRows", searchResults.getTotalElements());
        model.addAttribute("pageSize", pageSize);
        return "search_results";
    }
}