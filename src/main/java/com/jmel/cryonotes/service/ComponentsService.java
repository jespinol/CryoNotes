package com.jmel.cryonotes.service;

import com.jmel.cryonotes.model.Sample;
import com.jmel.cryonotes.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentsService {
    @Autowired
    private SampleRepository sampleRepository;

    public Page<Sample> getAllSamples(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Sample> pagedResult = sampleRepository.findAll(paging);
        return pagedResult;
    }

    public List<Sample> getSamplesMatching(String keyword) {
        // TODO make keyword nonnullable
        return sampleRepository.search(keyword);
    }
}
