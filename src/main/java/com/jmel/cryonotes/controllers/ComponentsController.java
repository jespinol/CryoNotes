package com.jmel.cryonotes.controllers;

import com.jmel.cryonotes.SampleRepository;
import com.jmel.cryonotes.models.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ComponentsController {

    @Autowired
    private SampleRepository sampleRepository;

    @GetMapping("/samples")
    public String viewSamples(Model model) {
        List<Sample> listSamples = sampleRepository.findAll();
        model.addAttribute("listSamples", listSamples);
        return "/components/samples_view";
    }

    @GetMapping("/samples/add")
    public String addSamples(Model model) {
        model.addAttribute("sample", new Sample());
        return "/components/samples_add";
    }

    @PostMapping("/samples")
    public String processSample(Sample sample, Model model) {
        sampleRepository.save(sample);
        return viewSamples(model);
    }
}

