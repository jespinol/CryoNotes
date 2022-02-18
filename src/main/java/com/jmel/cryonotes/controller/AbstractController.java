package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    protected AbstractController() throws ClassNotFoundException {
    }

    abstract String getViewName();

    abstract PagingAndSortingRepository<T, Long> getRepository();

    abstract List<T> getSearch(String keyword);

    abstract String getClassName();

    Class<?> cls = Class.forName(getClassName());

    abstract Map<String, String> getSummaryAttributes();

    abstract Map<String, String> getAllAttributes();

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
                          @RequestParam(defaultValue = "false") String ascending
    ) {
        Page<T> page = getRepository().findAll(getPaging(pageNo, pageSize, sortBy, ascending));
        model.addAttribute("currentObject", getViewName());
        model.addAttribute("allItems", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("ascending", ascending);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("totalRows", page.getTotalElements());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("summaryAttributes", getSummaryAttributes());
        return "view_all";
    }

    @GetMapping("/edit/{id}")
    public String viewEdit(Model model, @PathVariable Long id) {
        Optional<T> optional = getRepository().findById(id);
        T editItem = optional.get();
        model.addAttribute("editItem", editItem);
        model.addAttribute("allAttributes", getAllAttributes());
        model.addAttribute("currentObject", getViewName());
        return "view_edit";
    }

    @GetMapping("/{ids}")
    public String viewDetails(Model model, @PathVariable String ids) {
        System.out.println(ids);
        String[] idsStrArr = ids.split("&");
        List<Long> idsLongArr = new ArrayList<>();
        for (String str : idsStrArr) {
            idsLongArr.add(Long.parseLong(str));
        }
        List<T> selectedItems = (List<T>) getRepository().findAllById(idsLongArr);
        model.addAttribute("selectedItems", selectedItems);
        model.addAttribute("allAttributes", getAllAttributes());
        model.addAttribute("currentObject", getViewName());
        return "view_details";
    }

    @GetMapping("/add")
    public String add(Model model) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        model.addAttribute("newItem", cls.getDeclaredConstructor().newInstance());
        model.addAttribute("allAttributes", getAllAttributes());
        model.addAttribute("currentObject", getViewName());
        return "view_add";
    }

    @PostMapping("/save")
    public String save(@Valid T object, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentObject", getViewName());
            return "view_add";
        }
        getRepository().save(object);
        return viewAll(model, 0, 20, "id", "false");
    }

    @PostMapping("/save/{id}")
    public String editSave(@Valid T object, BindingResult bindingResult, Model model, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentObject", getViewName());
            return viewEdit(model, id);
        }
        getRepository().save(object);
        return viewAll(model, 0, 20, "id", "false");
    }

    @GetMapping("/search")
    public String searchSimple(Model model, @RequestParam(value = "keyword") String keyword) {
        List<T> searchResults = getSearch(keyword);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("summaryAttributes", getSummaryAttributes());
        model.addAttribute("currentObject", getViewName());
        return "view_search";
    }

}