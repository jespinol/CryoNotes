package com.jmel.cryonotes.repository;

import com.jmel.cryonotes.model.Sample;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SampleRepository extends PagingAndSortingRepository<Sample, Long> {
    @Query("SELECT s FROM Sample s WHERE CONCAT(s.date, s.name, s.category, s.creator, s.molecularWeight, s.isComplex, s.stoichiometry, s.comments) LIKE CONCAT('%',:keyword,'%')")
    List<Sample> simpleSearch(@Param("keyword") String keyword);

    @Query("SELECT s FROM Sample s " +
            "WHERE (:date IS null OR s.date LIKE %:date%) " +
            "AND (:name IS null OR s.name LIKE %:name%)" +
            "AND (:category IS null OR s.category LIKE %:category%)" +
            "AND (:creator IS null OR s.creator LIKE %:creator%)" +
            "AND (s.molecularWeight = :molecularWeight OR (:molecularWeight = -1 AND s.molecularWeight > 0))" +
            "AND (:isComplex IS null OR s.isComplex LIKE %:isComplex% )" +
            "AND (:stoichiometry IS null OR s.stoichiometry LIKE %:stoichiometry% )" +
            "AND (:comments IS null OR s.comments LIKE %:comments%)")
    List<Sample> advancedSearch(@Param("date") String date, @Param("name") String name, @Param("category") String category, @Param("creator") String creator, @Param("molecularWeight") int molecularWeight, @Param("isComplex") String iscComplex, @Param("stoichiometry") String stoichiometry, @Param("comments") String comments);
}
