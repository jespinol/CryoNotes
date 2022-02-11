package com.jmel.cryonotes.repository;

import com.jmel.cryonotes.model.Microscope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MicroscopeRepository extends PagingAndSortingRepository<Microscope, Long> {
}
