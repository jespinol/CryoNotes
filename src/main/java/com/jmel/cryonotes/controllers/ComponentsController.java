package com.jmel.cryonotes.controllers;

import com.jmel.cryonotes.models.Sample;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComponentsController {

    @GetMapping("/components/samples_view")
    public String viewSamples() {
        return "/components/samples_view";
    }

    @GetMapping("/components/sample_add")
    public String addSamples(Model model) {
        model.addAttribute("sample", new Sample());

        return "/comonents/sample_add";
    }
}

