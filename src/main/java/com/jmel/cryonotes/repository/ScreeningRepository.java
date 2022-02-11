package com.jmel.cryonotes.repository;

import com.jmel.cryonotes.model.Sample;
import com.jmel.cryonotes.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScreeningRepository extends PagingAndSortingRepository<Screening, Long> {
//    @Query("SELECT s FROM Screening s WHERE CONCAT(s.sampleName, s.sampleCategory, s.microscope, s.vitrification, s.pixelSize, s.result, s.collected, s.collectionTime, s.numMicrographs, s.storage, s.comments) LIKE CONCAT('%',:keyword,'%')")
//    public List<Screening> search(@Param("keyword") String keyword);
}
