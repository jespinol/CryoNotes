package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.model.Grid;
import com.jmel.cryonotes.model.Sample;
import com.jmel.cryonotes.repository.GridRepository;
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
@RequestMapping("/grids")
public class GridsController extends AbstractController<Grid>{

    private static final String VIEW_NAME = "grids";

    private static final String CLASS_NAME = "com.jmel.cryonotes.model.Grid";

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

        TemplateVariables concentrationVariables = new TemplateVariables("[Sample] mg/mL", true);
        concentrationVariables.addHtmlAttribute("type", "number");
        concentrationVariables.addHtmlAttribute("step", "0.1");
        concentrationVariables.addHtmlAttribute("min", "0");
        ATTRIBUTES.put("concentration", concentrationVariables);

        TemplateVariables bufferVariables = new TemplateVariables("Buffer and pH", false);
        ATTRIBUTES.put("buffer", bufferVariables);

        TemplateVariables saltVariables = new TemplateVariables("Salt and concentration", false);
        ATTRIBUTES.put("salt", saltVariables);

        TemplateVariables additivesVariables = new TemplateVariables("Additives (if any)", false);
        ATTRIBUTES.put("additives", additivesVariables);

        TemplateVariables sampleCommentsVariables = new TemplateVariables("Additional sample comments", false);
        ATTRIBUTES.put("sampleComments", sampleCommentsVariables);

        TemplateVariables gridMaterialVariables = new TemplateVariables("Grid type", true);
        ATTRIBUTES.put("gridMaterial", gridMaterialVariables);

        TemplateVariables gridSizeVariables = new TemplateVariables("Grid size", true);
        ATTRIBUTES.put("gridSize", gridSizeVariables);

        TemplateVariables glowDischargeVariables = new TemplateVariables("Glow discharge comments", false);
        ATTRIBUTES.put("glowDischarge", glowDischargeVariables);

        TemplateVariables vitrificationVariables = new TemplateVariables("Vitrification comments", true);
        ATTRIBUTES.put("vitrificationComments", vitrificationVariables);

        TemplateVariables storageVariables = new TemplateVariables("Storage comments", true);
        ATTRIBUTES.put("storageComments", storageVariables);

        TemplateVariables commentsVariables = new TemplateVariables("Comments", false);
        ATTRIBUTES.put("comments", commentsVariables);
    }

    @Autowired
    GridRepository repository;

    protected GridsController() throws ClassNotFoundException {
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
    public GridRepository getRepository() {
        return repository;
    }

    @Override
    public Page<Grid> getSearch(String keyword, Pageable pageable) {
        return repository.simpleSearch(keyword, pageable);
    }

    @GetMapping("/advanced_search/result")
    public String searchAdvanced(Model model, @RequestParam("date") String date,
                                 @RequestParam("name") String name,
                                 @RequestParam("category") String category,
                                 @RequestParam("creator") String creator,
                                 @RequestParam(value = "concentration", defaultValue = "-1") double concentration,
                                 @RequestParam("buffer") String buffer,
                                 @RequestParam(value = "salt") String salt,
                                 @RequestParam(value = "additives") String additives,
                                 @RequestParam(value = "sampleComments") String sampleComments,
                                 @RequestParam(value = "gridMaterial") String gridMaterial,
                                 @RequestParam(value = "glowDischarge") String glowDischarge,
                                 @RequestParam(value = "vitrificationComments") String vitrificationComments,
                                 @RequestParam(value = "storageComments") String storageComments,
                                 @RequestParam("comments") String comments, @RequestParam(defaultValue = "0") Integer pageNo,
                                 @RequestParam(defaultValue = "20") Integer pageSize,
                                 @RequestParam(defaultValue = "id") String sortBy,
                                 @RequestParam(defaultValue = "false") String ascending) {
        Pageable pageRequest = getPaging(pageNo, pageSize, sortBy, ascending);
        Page<Grid> searchResults = repository.advancedSearch(pageRequest, date, name, category, creator, concentration, buffer, salt, additives, sampleComments, gridMaterial, glowDischarge, vitrificationComments, storageComments, comments);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("currentObject", getViewName());
        model.addAttribute("viewType", "advanced_search/result?" + "date=" + date + "&" + "name=" + name + "&" + "category=" + category + "&" + "creator=" + creator + "&" + "concentration=" + concentration + "&" + "buffer=" + buffer + "&" + "salt=" + salt + "&" + "additives=" + additives + "&" + "sampleComments=" + sampleComments + "&" + "gridMaterial=" + gridMaterial + "&" + "glowDischarge=" + glowDischarge + "&" + "vitrificationComments=" + vitrificationComments + "&" + "storageComments=" + storageComments + "&" + "comments=" + comments + "&");
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", searchResults.getTotalPages());
        model.addAttribute("ascending", ascending);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("totalRows", searchResults.getTotalElements());
        model.addAttribute("pageSize", pageSize);
        return "search_results";
    }
}
