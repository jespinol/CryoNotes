package com.jmel.cryonotes.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class GeneralController {

    @RequestMapping("/")
    public void handleRequest() {
        throw new RuntimeException("test");
    }
}
