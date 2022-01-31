package com.jmel.cryonotes;

import com.jmel.cryonotes.models.Sample;
import com.jmel.cryonotes.models.data.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleService {
    @Autowired
    private SampleRepository sampleRepository;

    public List<Sample> listAll(String keyword) {
        if (keyword != null) {
            return sampleRepository.search(keyword);
        }
        return sampleRepository.findAll();
    }
}
