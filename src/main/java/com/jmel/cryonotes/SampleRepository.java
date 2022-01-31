package com.jmel.cryonotes;

import com.jmel.cryonotes.models.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
