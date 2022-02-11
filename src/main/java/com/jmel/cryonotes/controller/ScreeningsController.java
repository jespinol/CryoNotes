package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.model.Screening;
import com.jmel.cryonotes.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/screenings")
public class ScreeningsController extends AbstractController<Screening> {

    private static final String VIEW_NAME = "screenings";

    private static final String CLASS_NAME = "com.jmel.cryonotes.model.Screening";

    @Autowired
    ScreeningRepository repository;

    protected ScreeningsController() throws ClassNotFoundException {
    }

    String getViewName() {
        return VIEW_NAME;
    }

    String getClassName() {
        return CLASS_NAME;
    }

    @Override
    ScreeningRepository getRepository() {
        return repository;
    }
}