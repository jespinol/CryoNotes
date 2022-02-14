package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.model.Sample;
import com.jmel.cryonotes.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/samples")
public class SamplesController extends AbstractController<Sample> {

    private static final String VIEW_NAME = "samples";

    private static final String CLASS_NAME = "com.jmel.cryonotes.model.Sample";

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

    @Override
    public SampleRepository getRepository() {
        return repository;
    }

    @Override
    public List<Sample> getSearch(String keyword) {
        return repository.search(keyword);
    }
}