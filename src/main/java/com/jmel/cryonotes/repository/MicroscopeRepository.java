package com.jmel.cryonotes.repository;

import com.jmel.cryonotes.model.Microscope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MicroscopeRepository extends PagingAndSortingRepository<Microscope, Long> {
    @Query("SELECT s FROM Microscope s WHERE CONCAT(s.name, s.type, s.facility, s.voltage, s.cs, s.detectors, s.comments) LIKE CONCAT('%',:keyword,'%')")
    public List<Microscope> simpleSearch(@Param("keyword") String keyword);
}
