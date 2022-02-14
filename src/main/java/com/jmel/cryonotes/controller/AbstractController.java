package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractController<U> {

    @Autowired
    SampleRepository sampleRepository;

    protected AbstractController() throws ClassNotFoundException {
    }

    abstract String getViewName();

    abstract PagingAndSortingRepository<U, Long> getRepository();

    abstract String getClassName();

    Class<?> cls = Class.forName(getClassName());

    public PageRequest getPaging(Integer pageNo, Integer pageSize, String sortBy, String ascending) {
        PageRequest paging;
        if (ascending.equals("true")) {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        } else {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        }
        return paging;
    }

    @GetMapping("/all")
    public String viewAll(Model model,
                          @RequestParam(defaultValue = "0") Integer pageNo,
                          @RequestParam(defaultValue = "30") Integer pageSize,
                          @RequestParam(defaultValue = "id") String sortBy,
                          @RequestParam(defaultValue = "false") String ascending) {
        Page<U> page = getRepository().findAll(getPaging(pageNo, pageSize, sortBy, ascending));
        model.addAttribute("currentObject", getViewName());
        model.addAttribute("allItems", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        return getViewName() + "_view_all";
    }

    @GetMapping("/{id}")
    public String viewSingle(Model model, @PathVariable Long id) {
        Optional<U> optional = getRepository().findById(id);
        U item = optional.get();
        model.addAttribute("singleItem", item);
        model.addAttribute("currentObject", getViewName());
        return getViewName() + "_view_single";
    }

    @GetMapping("/add")
    public String add(Model model) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        model.addAttribute("newItem", cls.getDeclaredConstructor().newInstance());
        model.addAttribute("currentObject", getViewName());
        return getViewName() + "_add";
    }

    @PostMapping("/save")
    public String save(@Valid U object, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentObject", getViewName());
            return getViewName() + "_add";
        }
        getRepository().save(object);
        return viewAll(model, 0, 30, "id", "false");
    }

    @PostMapping("/save/{id}")
    public String editSave(@Valid U object, BindingResult bindingResult, Model model, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentObject", getViewName());
            return viewSingle(model, id);
        }
        getRepository().save(object);
        return viewAll(model, 0, 30, "id", "false");
    }

    @GetMapping("/search")
    public String searchSimple(Model model, @RequestParam(value = "keyword") String keyword) {
        List<?> searchResults = sampleRepository.search(keyword);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("currentObject", getViewName());
        return getViewName() + "_view_search";
    }

}