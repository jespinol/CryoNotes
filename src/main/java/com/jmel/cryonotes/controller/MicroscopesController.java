//package com.jmel.cryonotes.controller;
//
//import com.jmel.cryonotes.model.Microscope;
//import com.jmel.cryonotes.repository.MicroscopeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/microscopes")
//public class MicroscopesController extends AbstractController<Microscope> {
//
//    private static final String VIEW_NAME = "microscopes";
//
//    private static final String CLASS_NAME = "com.jmel.cryonotes.model.Microscope";
//
//    private static final Map<String, String> SUMMARY_ATTRIBUTES = new LinkedHashMap<>();
//
//    static {
//        SUMMARY_ATTRIBUTES.put("Name", "name");
//        SUMMARY_ATTRIBUTES.put("Type", "type");
//        SUMMARY_ATTRIBUTES.put("Facility", "facility");
//        SUMMARY_ATTRIBUTES.put("Voltage", "voltage");
//        SUMMARY_ATTRIBUTES.put("Spherical aberration", "cs");
//        SUMMARY_ATTRIBUTES.put("Detectors", "detectors");
//    }
//
//    private static final Map<String, String> ALL_ATTRIBUTES = new LinkedHashMap<>();
//
//    static {
//        ALL_ATTRIBUTES.put("Name", "name");
//        ALL_ATTRIBUTES.put("Type", "type");
//        ALL_ATTRIBUTES.put("Facility", "facility");
//        ALL_ATTRIBUTES.put("Voltage", "voltage");
//        ALL_ATTRIBUTES.put("Spherical aberration", "cs");
//        ALL_ATTRIBUTES.put("Detectors", "detectors");
//        ALL_ATTRIBUTES.put("Comments", "comments");
//    }
//
//    @Autowired
//    MicroscopeRepository repository;
//
//    protected MicroscopesController() throws ClassNotFoundException {
//    }
//
//    String getViewName() {
//        return VIEW_NAME;
//    }
//
//    String getClassName() {
//        return CLASS_NAME;
//    }
//
//    Map<String, String> getSummaryAttributes() {
//        return SUMMARY_ATTRIBUTES;
//    }
//
//    Map<String, String> getAllAttributes() {
//        return ALL_ATTRIBUTES;
//    }
//
//    @Override
//    public MicroscopeRepository getRepository() {
//        return repository;
//    }
//
//    @Override
//    public List<Microscope> getSearch(String keyword) {
//        return repository.simpleSearch(keyword);
//    }
//}