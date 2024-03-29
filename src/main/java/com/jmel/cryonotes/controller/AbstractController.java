package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.repository.GridRepository;
import com.jmel.cryonotes.repository.MicroscopeRepository;
import com.jmel.cryonotes.repository.SampleRepository;
import com.jmel.cryonotes.service.TemplateVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public abstract class AbstractController<T> {

    @Autowired
    SampleRepository sampleRepository;

    @Autowired
    MicroscopeRepository microscopeRepository;

    @Autowired
    GridRepository gridRepository;

    protected AbstractController() throws ClassNotFoundException {
    }

    abstract String getViewName();

    abstract PagingAndSortingRepository<T, Long> getRepository();

    abstract Page<T> getSearch(String keyword, Pageable pageable);

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
        model.addAttribute("viewType", "all?");
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("allItems", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("ascending", ascending);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("totalRows", page.getTotalElements());
        model.addAttribute("pageSize", pageSize);
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
        model.addAttribute("grid", gridRepository.findAll());
        return "edit";
    }

    @PostMapping("/save")
    public String save(@Valid T object, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentObject", getViewName());
            model.addAttribute("saveError", true);
            return "add";
        }
        getRepository().save(object);
        model.addAttribute("saveSuccess", true);
        return viewAll(model, 0, 20, "id", "false");
    }

    @PostMapping("/save/{id}")
    public String save(@Valid T object, BindingResult bindingResult, Model model, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentObject", getViewName());
            model.addAttribute("saveError", true);
            return edit(model, id);
        }
        getRepository().save(object);
        model.addAttribute("saveSuccess", true);
        return viewDetails(model, String.valueOf(id));
    }

    @GetMapping("/search/result")
    public String searchSimple(Model model,
                               @RequestParam("keyword") String keyword,
                               @RequestParam(defaultValue = "0") Integer pageNo,
                               @RequestParam(defaultValue = "20") Integer pageSize,
                               @RequestParam(defaultValue = "id") String sortBy,
                               @RequestParam(defaultValue = "false") String ascending) {
        Pageable pageRequest = getPaging(pageNo, pageSize, sortBy, ascending);
        Page<T> searchResults = getSearch(keyword, pageRequest);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("currentObject", getViewName());
        model.addAttribute("viewType", "search/result?keyword=" + keyword +"&");
        model.addAttribute("allItems", searchResults.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", searchResults.getTotalPages());
        model.addAttribute("ascending", ascending);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("totalRows", searchResults.getTotalElements());
        model.addAttribute("pageSize", pageSize);
        return "search_results";
    }

    @GetMapping("/advanced_search")
    public String advancedSearchView(Model model) {
        model.addAttribute("attributes", getAttributes());
        model.addAttribute("currentObject", getViewName());
        model.addAttribute("sample", sampleRepository.findAll());
        model.addAttribute("microscope", microscopeRepository.findAll());
        model.addAttribute("grid", gridRepository.findAll());
        return "advanced_search";
    }
}