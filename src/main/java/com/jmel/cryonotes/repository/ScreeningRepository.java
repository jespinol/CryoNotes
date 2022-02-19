package com.jmel.cryonotes.repository;

import com.jmel.cryonotes.model.Screening;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreeningRepository extends PagingAndSortingRepository<Screening, Long> {
    @Query("SELECT s FROM Screening s WHERE CONCAT(s.date, s.name, s.category, s.creator, s.microscope, s.grid, s.result, s.wasCollected, s.wasStored, s.comments) LIKE CONCAT('%',:keyword,'%')")
    public List<Screening> simpleSearch(@Param("keyword") String keyword);
}
