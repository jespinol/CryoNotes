package com.jmel.cryonotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComponentsController {

    @GetMapping("/components/samples_view")
    public String viewSamples() {
        return "/components/samples_view";
    }
}

