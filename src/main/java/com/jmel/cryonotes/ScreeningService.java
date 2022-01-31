package com.jmel.cryonotes;

import com.jmel.cryonotes.models.Sample;
import com.jmel.cryonotes.models.Screening;
import com.jmel.cryonotes.models.data.SampleRepository;
import com.jmel.cryonotes.models.data.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningService {
    @Autowired
    private ScreeningRepository screeningRepository;

    public List<Screening> getScreeningsMatching(String keyword) {
        // TODO make keyword nonnullable
        return screeningRepository.search(keyword);
    }
}
