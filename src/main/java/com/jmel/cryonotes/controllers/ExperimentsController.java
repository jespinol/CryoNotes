package com.jmel.cryonotes.controllers;

import com.jmel.cryonotes.ScreeningService;
import com.jmel.cryonotes.models.Sample;
import com.jmel.cryonotes.models.data.ScreeningRepository;
import com.jmel.cryonotes.models.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ExperimentsController {

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private ScreeningService screeningService;

    @GetMapping("/screenings")
    public String vieScreenings(Model model) {
        List<Screening> listScreenings = screeningRepository.findAll();
        model.addAttribute("listScreenings", listScreenings);
        return "/experiments/screenings_view";
    }

    @GetMapping("/screenings/search")
    public String viewScreenings(Model model, @RequestParam(value="keyword") String keyword) {
        List<Screening> listScreenings = screeningService.getScreeningsMatching(keyword);
        model.addAttribute("listScreenings", listScreenings);
        return "/experiments/screenings_view";
    }

    @GetMapping("/screenings/add")
    public String addScreenings(Model model) {
        model.addAttribute("screening", new Screening());
        return "/experiments/screenings_add";
    }

    @PostMapping("/screenings")
    public String processSample(Screening screening, Model model) {
        screeningRepository.save(screening);
        return vieScreenings(model);
    }

    @GetMapping("/screening/{screeningId}")
    public String viewSingleScreening(Model model, @PathVariable Long screeningId) {
        Optional optScreening = screeningRepository.findById(screeningId);
        Screening individualScreening = (Screening) optScreening.get();
        model.addAttribute("individualScreening", individualScreening);
        return ("/experiments/screening_view");
    }
}

