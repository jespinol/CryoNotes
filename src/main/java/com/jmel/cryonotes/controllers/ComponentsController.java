package com.jmel.cryonotes.controllers;

import com.jmel.cryonotes.ComponentsService;
import com.jmel.cryonotes.SampleService;
import com.jmel.cryonotes.models.Microscope;
import com.jmel.cryonotes.models.Sample;
import com.jmel.cryonotes.models.data.MicroscopeRepository;
import com.jmel.cryonotes.models.data.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private SampleService sampleService;

    @Autowired
    private ComponentsService componentsService;

//    @GetMapping("/samples")
//    public String viewSamples(Model model) {
//        List<Sample> listSamples = (List<Sample>) sampleRepository.findAll();
//        model.addAttribute("listSamples", listSamples);
//        return "/components/samples_view";
//    }

    @GetMapping("/samples")
    public String viewSamples(Model model, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "2") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy){
        Page<Sample> page = componentsService.getAllSamples(pageNo, pageSize, sortBy);
        List<Sample> list = page.getContent();
        model.addAttribute("listSamples", list);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        return "/components/samples_view";
//        return new ResponseEntity<List<Sample>>(list, new HttpHeaders(), HttpStatus.OK);
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
//        List<Sample> listSamples = sampleService.getSamplesMatching(keyword);
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

