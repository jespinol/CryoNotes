package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.model.Sample;
import com.jmel.cryonotes.repository.SampleRepository;
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
@RequestMapping("/samples")
public class SamplesController extends AbstractController<Sample> {

    private static final String VIEW_NAME = "samples";

    private static final String CLASS_NAME = "com.jmel.cryonotes.model.Sample";

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
    public Page<Sample> getSearch(String keyword, Pageable pageable) {
        return repository.simpleSearch(keyword, pageable);
    }

    @GetMapping("/advanced_search/result")
    public String searchAdvanced(Model model, @RequestParam("date") String date, @RequestParam("name") String name, @RequestParam("category") String category, @RequestParam("creator") String creator, @RequestParam(value = "molecularWeight", defaultValue = "-1") int molecularWeight, @RequestParam("isComplex") String iscComplex, @RequestParam(value = "stoichiometry") String stoichiometry, @RequestParam("comments") String comments,
                                 @RequestParam(defaultValue = "0") Integer pageNo,
                                 @RequestParam(defaultValue = "20") Integer pageSize,
                                 @RequestParam(defaultValue = "id") String sortBy,
                                 @RequestParam(defaultValue = "false") String ascending) {
        Pageable pageRequest = getPaging(pageNo, pageSize, sortBy, ascending);
        Page<Sample> searchResults = repository.advancedSearch(pageRequest, date, name, category, creator, molecularWeight, iscComplex, stoichiometry, comments);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("currentObject", getViewName());
        model.addAttribute("viewType", "advanced_search/result?" + "date=" + date + "&" + "name=" + name + "&" + "category=" + category + "&" + "creator=" + creator + "&" + "molecularWeight=" + molecularWeight + "&" + "isComplex=" + iscComplex + "&" + "stoichiometry=" + stoichiometry + "&" + "comments=" + comments + "&");
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", searchResults.getTotalPages());
        model.addAttribute("ascending", ascending);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("totalRows", searchResults.getTotalElements());
        model.addAttribute("pageSize", pageSize);
        return "search_results";
    }
}