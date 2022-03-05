package com.jmel.cryonotes.repository;

import com.jmel.cryonotes.model.Grid;
import com.jmel.cryonotes.model.Sample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GridRepository extends PagingAndSortingRepository<Grid, Long> {
    @Query("SELECT s FROM Grid s WHERE CONCAT(s.date, s.name, s.category, s.creator, s.concentration, s.buffer, s.salt, s.additives, s.sampleComments, s.gridMaterial, s.glowDischarge, s.vitrificationComments, s.storageComments, s.comments) LIKE CONCAT('%',:keyword,'%')")
    Page<Grid> simpleSearch(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT s FROM Grid s " +
            "WHERE (:date IS null OR s.date LIKE %:date%) " +
            "AND (:name IS null OR s.name LIKE %:name%)" +
            "AND (:category IS null OR s.category LIKE %:category%)" +
            "AND (:creator IS null OR s.creator LIKE %:creator%)" +
            "AND (s.concentration = :concentration OR (:concentration < 0 AND s.concentration > 0))" +
            "AND (:buffer IS null OR s.buffer LIKE %:buffer% )" +
            "AND (:salt IS null OR s.salt LIKE %:salt% )" +
            "AND (:additives IS null OR s.additives LIKE %:additives% )" +
            "AND (:sampleComments IS null OR s.sampleComments LIKE %:sampleComments% )" +
            "AND (:gridMaterial IS null OR s.gridMaterial LIKE %:gridMaterial% )" +
            "AND (:glowDischarge IS null OR s.glowDischarge LIKE %:glowDischarge% )" +
            "AND (:vitrificationComments IS null OR s.vitrificationComments LIKE %:vitrificationComments% )" +
            "AND (:storageComments IS null OR s.storageComments LIKE %:storageComments% )" +
            "AND (:comments IS null OR s.comments LIKE %:comments%)")
    Page<Grid> advancedSearch(Pageable pageable, @Param("date") String date, @Param("name") String name,
                              @Param("category") String category, @Param("creator") String creator,
                              @Param("concentration") double concentration, @Param("buffer") String buffer,
                              @Param("salt") String salt, @Param("additives") String additives,
                              @Param("sampleComments") String sampleComments,
                              @Param("gridMaterial") String gridMaterial, @Param("glowDischarge") String glowDischarge,
                              @Param("vitrificationComments") String vitrificationComments,
                              @Param("storageComments") String storageComments, @Param("comments") String comments);

}
