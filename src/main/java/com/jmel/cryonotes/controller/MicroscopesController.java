package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.model.Microscope;
import com.jmel.cryonotes.repository.MicroscopeRepository;
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
@RequestMapping("/microscopes")
public class MicroscopesController extends AbstractController<Microscope> {

    private static final String VIEW_NAME = "microscopes";

    private static final String CLASS_NAME = "com.jmel.cryonotes.model.Microscope";

    private static final Map<String, TemplateVariables> ATTRIBUTES = new LinkedHashMap<>();

    static {
        TemplateVariables nameVariables = new TemplateVariables("Microscope Name", true);
        ATTRIBUTES.put("name", nameVariables);

        TemplateVariables typeVariables = new TemplateVariables("Type", true);
        ATTRIBUTES.put("type", typeVariables);

        TemplateVariables facilityVariables = new TemplateVariables("Facility", true);
        ATTRIBUTES.put("facility", facilityVariables);

        TemplateVariables voltageVariables = new TemplateVariables("Voltage (kV)", false);
        voltageVariables.addHtmlAttribute("type", "number");
        voltageVariables.addHtmlAttribute("min", "0");
        ATTRIBUTES.put("voltage", voltageVariables);

        TemplateVariables csVariables = new TemplateVariables("Spherical Aberration (cs)", false);
        csVariables.addHtmlAttribute("type", "number");
        csVariables.addHtmlAttribute("step", "0.1");
        csVariables.addHtmlAttribute("min", "0");
        ATTRIBUTES.put("cs", csVariables);

        TemplateVariables detectorsVariables = new TemplateVariables("Detectors", false);
        ATTRIBUTES.put("detectors", detectorsVariables);
        ;

        TemplateVariables commentsVariables = new TemplateVariables("Comments", false);
        ATTRIBUTES.put("comments", commentsVariables);
    }

    @Autowired
    MicroscopeRepository repository;

    protected MicroscopesController() throws ClassNotFoundException {
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
    public MicroscopeRepository getRepository() {
        return repository;
    }

    @Override
    public Page<Microscope> getSearch(String keyword, Pageable pageable) {
        return repository.simpleSearch(keyword, pageable);
    }


    @GetMapping("/advanced_search/result")
    public String searchAdvanced(Model model, @RequestParam("name") String name, @RequestParam("type") String type, @RequestParam("facility") String facility, @RequestParam(value = "voltage", defaultValue = "-1") int voltage, @RequestParam(value = "cs", defaultValue = "-1") double cs, @RequestParam("detectors") String detectors, @RequestParam("comments") String comments) {
        List<Microscope> searchResults = repository.advancedSearch(name, type, facility, voltage, cs, detectors, comments);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("currentObject", getViewName());
        return "search_results";
    }
}