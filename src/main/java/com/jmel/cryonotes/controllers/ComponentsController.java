package com.jmel.cryonotes.controllers;

import com.jmel.cryonotes.SampleService;
import com.jmel.cryonotes.models.Sample;
import com.jmel.cryonotes.models.data.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ComponentsController {

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private SampleService sampleService;

    @GetMapping("/samples")
    public String viewSamples(Model model) {
        List<Sample> listSamples = sampleRepository.findAll();
        model.addAttribute("listSamples", listSamples);
        return "/components/samples_view";
    }

    @GetMapping("/samples/search")
    public String viewSamples(Model model, @RequestParam(value="keyword") String keyword) {
        List<Sample> listSamples = sampleService.getSamplesMatching(keyword);
        model.addAttribute("listSamples", listSamples);
        return "/components/samples_view";
    }

    @GetMapping("/samples/add")
    public String addSamples(Model model) {
        model.addAttribute("sample", new Sample());
        return "/components/samples_add";
    }

    @PostMapping("/samples/save")
    public String processSample(@Valid Sample sample, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/components/samples_add";
        }
        sampleRepository.save(sample);
        return viewSamples(model);
    }

    @GetMapping("/sample/{sampleId}")
    public String viewSingleSample(Model model, @PathVariable Long sampleId) {
        Optional optSample = sampleRepository.findById(sampleId);
        Sample individualSample = (Sample) optSample.get();
        model.addAttribute("individualSample", individualSample);
        return ("/components/sample_view");
    }
}

