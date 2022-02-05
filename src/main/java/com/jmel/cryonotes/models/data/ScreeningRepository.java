package com.jmel.cryonotes.models.data;

import com.jmel.cryonotes.models.Sample;
import com.jmel.cryonotes.models.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
//    @Query("SELECT s FROM Screening s WHERE CONCAT(s.sampleName, s.sampleCategory, s.microscope, s.vitrification, s.pixelSize, s.result, s.collected, s.collectionTime, s.numMicrographs, s.storage, s.comments) LIKE CONCAT('%',:keyword,'%')")
//    public List<Screening> search(@Param("keyword") String keyword);
}
