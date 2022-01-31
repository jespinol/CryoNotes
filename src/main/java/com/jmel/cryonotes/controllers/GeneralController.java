package com.jmel.cryonotes.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class GeneralController {

    @RequestMapping("/")
    public void handleRequest() {
        throw new RuntimeException("test");
    }
}
