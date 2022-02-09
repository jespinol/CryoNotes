package com.jmel.cryonotes.repository;

import com.jmel.cryonotes.model.Sample;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SampleRepository  extends PagingAndSortingRepository<Sample, Long> {
    @Query("SELECT s FROM Sample s WHERE CONCAT(s.sampleName, s.sampleCategory, s.molecularWeight, s.isComplex, s.stoichiometry) LIKE CONCAT('%',:keyword,'%')")
    public List<Sample> search(@Param("keyword") String keyword);
}
