package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.service.ComponentsService;
import com.jmel.cryonotes.model.Microscope;
import com.jmel.cryonotes.model.Sample;
import com.jmel.cryonotes.repository.MicroscopeRepository;
import com.jmel.cryonotes.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ComponentsController {

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private MicroscopeRepository microscopeRepository;

    @Autowired
    private ComponentsService componentsService;

    @GetMapping("/samples")
    public String viewSamples(Model model, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "3") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy){
        Page<Sample> page = componentsService.getAllSamples(pageNo, pageSize, sortBy);
        List<Sample> list = page.getContent();
        model.addAttribute("listSamples", list);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
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
        return viewSamples(model, 0, 100, "id");
    }

    @GetMapping("/sample/{sampleId}")
    public String viewSingleSample(Model model, @PathVariable Long sampleId) {
        Optional optSample = sampleRepository.findById(sampleId);
        Sample individualSample = (Sample) optSample.get();
        model.addAttribute("individualSample", individualSample);
        return ("/components/sample_view");
    }

//    @GetMapping("/samples/search")
//    public String viewSamples(Model model, @RequestParam(value="keyword") String keyword) {
//        List<Sample> listSamples = componentsService.getSamplesMatching(keyword);
//        model.addAttribute("listSamples", listSamples);
//        return "/components/samples_view";
//    }

    @GetMapping("/microscopes")
    public String viewMicroscopes(Model model) {
        List<Microscope> listMicroscopes = microscopeRepository.findAll();
        model.addAttribute("listMicroscopes", listMicroscopes);
        return "/components/microscopes_view";
    }

    @GetMapping("/microscopes/add")
    public String addMicroscopes(Model model) {
        model.addAttribute("microscope", new Microscope());
        return "/components/microscopes_add";
    }

    @PostMapping("/microscopes/save")
    public String processMicroscope(@Valid Microscope microscope, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/components/microscopes_add";
        }
        microscopeRepository.save(microscope);
        return viewMicroscopes(model);
    }

    @GetMapping("/microscope/{microscopeId}")
    public String viewSingleMicroscope(Model model, @PathVariable Long microscopeId) {
        Optional optMicroscope = microscopeRepository.findById(microscopeId);
        Microscope individualMicroscope = (Microscope) optMicroscope.get();
        model.addAttribute("individualMicroscope", individualMicroscope);
        return ("/components/microscope_view");
    }
}

