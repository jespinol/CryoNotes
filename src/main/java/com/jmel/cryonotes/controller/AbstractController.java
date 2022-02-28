package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.repository.MicroscopeRepository;
import com.jmel.cryonotes.repository.SampleRepository;
import com.jmel.cryonotes.service.TemplateVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public abstract class AbstractController<T> {

    @Autowired
    SampleRepository sampleRepository;

    @Autowired
    MicroscopeRepository microscopeRepository;

    protected AbstractController() throws ClassNotFoundException {
    }

    abstract String getViewName();

    abstract PagingAndSortingRepository<T, Long> getRepository();

    abstract List<T> getSearch(String keyword);

//    abstract List<T> getAdvancedSearch(String date, String name, String category, String creator, int molecularWeight, String iscComplex, String stoichiometry, String comments);
//
//    abstract List<T> getAdvancedSearch(String name, String type, String facility, int voltage, double cs, String detectors, String comments);

    abstract String getClassName();

    Class<?> cls = Class.forName(getClassName());

    abstract Map<String, TemplateVariables> getAttributes();

    public PageRequest getPaging(Integer pageNo, Integer pageSize, String sortBy, String ascending) {
        if (ascending.equals("true")) {
            return PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        }
        return PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
    }

    @GetMapping("/all")
    public String viewAll(Model model,
                          @RequestParam(defaultValue = "0") Integer pageNo,
                          @RequestParam(defaultValue = "20") Integer pageSize,
                          @RequestParam(defaultValue = "id") String sortBy,
                          @RequestParam(defaultValue = "false") String ascending) {
        Page<T> page = getRepository().findAll(getPaging(pageNo, pageSize, sortBy, ascending));
        model.addAttribute("currentObject", getViewName());
        model.addAttribute("allItems", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("ascending", ascending);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("totalRows", page.getTotalElements());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("attributes", getAttributes());
        return "view_all";
    }

    @GetMapping("/add")
    public String add(Model model) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        model.addAttribute("newItem", cls.getDeclaredConstructor().newInstance());
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("currentObject", getViewName());
        model.addAttribute("sample", sampleRepository.findAll());
        model.addAttribute("microscope", microscopeRepository.findAll());
        return "add";
    }

    @GetMapping("/details")
    public String viewDetails(Model model, @RequestParam("ids") String ids) {
        System.out.println(ids);
        String[] idsStrArr = ids.split(",");
        List<Long> idsLongArr = new ArrayList<>();
        for (String str : idsStrArr) {
            idsLongArr.add(Long.parseLong(str));
        }
        List<T> selectedItems = (List<T>) getRepository().findAllById(idsLongArr);
        model.addAttribute("selectedItems", selectedItems);
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("currentObject", getViewName());
        return "view_details";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") Long id) {
        Optional<T> optional = getRepository().findById(id);
        T editItem = optional.get();
        model.addAttribute("editItem", editItem);
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("currentObject", getViewName());
        model.addAttribute("sample", sampleRepository.findAll());
        model.addAttribute("microscope", microscopeRepository.findAll());
        return "edit";
    }

    @PostMapping("/save")
    public String save(@Valid T object, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentObject", getViewName());
            return "add";
        }
        getRepository().save(object);
        return viewAll(model, 0, 20, "id", "false");
    }

    @PostMapping("/save/{id}")
    public String save(@Valid T object, BindingResult bindingResult, Model model, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentObject", getViewName());
            return edit(model, id);
        }
        getRepository().save(object);
        return viewDetails(model, String.valueOf(id));
    }

    @GetMapping("/search/result")
    public String searchSimple(Model model, @RequestParam("keyword") String keyword) {
        List<T> searchResults = getSearch(keyword);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("currentObject", getViewName());
        return "search_results";
    }

    @GetMapping("/advanced_search")
    public String advancedSearchView(Model model) {
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("currentObject", getViewName());
        model.addAttribute("sample", sampleRepository.findAll());
        model.addAttribute("microscope", microscopeRepository.findAll());
        return "advanced_search";
    }
}