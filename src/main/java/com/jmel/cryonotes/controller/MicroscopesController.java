package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.model.Microscope;
import com.jmel.cryonotes.model.Sample;
import com.jmel.cryonotes.repository.MicroscopeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/microscopes")
public class MicroscopesController extends AbstractController<Microscope> {

    private static final String VIEW_NAME = "microscopes";

    private static final String CLASS_NAME = "com.jmel.cryonotes.model.Microscope";

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

    @Override
    public MicroscopeRepository getRepository() {
        return repository;
    }

    @Override
    public List<Microscope> getSearch(String keyword) {
        return repository.search(keyword);
    }
}