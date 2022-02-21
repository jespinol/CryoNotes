package com.jmel.cryonotes.repository;

import com.jmel.cryonotes.model.Microscope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MicroscopeRepository extends PagingAndSortingRepository<Microscope, Long> {
    @Query("SELECT s FROM Microscope s WHERE CONCAT(s.name, s.type, s.facility, s.voltage, s.cs, s.detectors, s.comments) LIKE CONCAT('%',:keyword,'%')")
    List<Microscope> simpleSearch(@Param("keyword") String keyword);

    @Query("SELECT s FROM Microscope s " +
            "WHERE (:name IS null OR s.name LIKE %:name%)" +
            "AND (:type IS null OR s.type LIKE %:type%)" +
            "AND (:facility IS null OR s.facility LIKE %:facility%)" +
            "AND (s.voltage = :voltage OR (:voltage < 0 AND s.voltage > 0))" +
            "AND (s.cs = :cs OR (:cs < 0 AND s.cs > 0))" +
            "AND (:detectors IS null OR s.detectors LIKE %:detectors% )" +
            "AND (:comments IS null OR s.comments LIKE %:comments%)")
    List<Microscope> advancedSearch(@Param("name") String name, @Param("type") String type, @Param("facility") String facility, @Param("voltage") int voltage, @Param("cs") double cs, @Param("detectors") String detectors, @Param("comments") String comments);
}
