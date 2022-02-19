//package com.jmel.cryonotes.controller;
//
//import com.jmel.cryonotes.model.Screening;
//import com.jmel.cryonotes.repository.ScreeningRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/screenings")
//public class ScreeningsController extends AbstractController<Screening> {
//
//    private static final String VIEW_NAME = "screenings";
//
//    private static final String CLASS_NAME = "com.jmel.cryonotes.model.Screening";
//
//    private static final Map<String, String> SUMMARY_ATTRIBUTES = new LinkedHashMap<>();
//
//    static {
//        SUMMARY_ATTRIBUTES.put("Date", "date");
//        SUMMARY_ATTRIBUTES.put("Screening name", "name");
//        SUMMARY_ATTRIBUTES.put("Category", "category");
//        SUMMARY_ATTRIBUTES.put("Created by", "creator");
//        SUMMARY_ATTRIBUTES.put("Microscope", "microscope");
//        SUMMARY_ATTRIBUTES.put("Grid", "grid");
//        SUMMARY_ATTRIBUTES.put("Result", "result");
//        SUMMARY_ATTRIBUTES.put("Data collection", "wasCollected");
//        SUMMARY_ATTRIBUTES.put("Grid storage", "wasStored");
//    }
//
//    private static final Map<String, String> ALL_ATTRIBUTES = new LinkedHashMap<>();
//
//    static {
//        ALL_ATTRIBUTES.put("Date", "date");
//        ALL_ATTRIBUTES.put("Screening name", "name");
//        ALL_ATTRIBUTES.put("Category", "category");
//        ALL_ATTRIBUTES.put("Created by", "creator");
//        ALL_ATTRIBUTES.put("Microscope", "microscope");
//        ALL_ATTRIBUTES.put("Grid", "grid");
//        ALL_ATTRIBUTES.put("Result", "result");
//        ALL_ATTRIBUTES.put("Data collection", "wasCollected");
//        ALL_ATTRIBUTES.put("Grid storage", "wasStored");
//        ALL_ATTRIBUTES.put("Comments", "comments");
//    }
//
//    @Autowired
//    ScreeningRepository repository;
//
//    protected ScreeningsController() throws ClassNotFoundException {
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
//    ScreeningRepository getRepository() {
//        return repository;
//    }
//
//    @Override
//    public List<Screening> getSearch(String keyword) {
//        return repository.simpleSearch(keyword);
//    }
//}