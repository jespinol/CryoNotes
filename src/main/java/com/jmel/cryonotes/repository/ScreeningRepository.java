package com.jmel.cryonotes.repository;

import com.jmel.cryonotes.model.Screening;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreeningRepository extends PagingAndSortingRepository<Screening, Long> {
    @Query("SELECT s FROM Screening s WHERE CONCAT(s.date, s.name, s.category, s.creator, s.microscope, s.grid, s.result, s.wasCollected, s.wasStored, s.comments) LIKE CONCAT('%',:keyword,'%')")
    List<Screening> simpleSearch(@Param("keyword") String keyword);

    @Query("SELECT s FROM Screening s " +
            "WHERE (:date IS null OR s.date LIKE %:date%) " +
            "AND (:name IS null OR s.name LIKE %:name%)" +
            "AND (:category IS null OR s.category LIKE %:category%)" +
            "AND (:creator IS null OR s.creator LIKE %:creator%)" +

            "AND (:grid IS null OR s.grid LIKE %:grid% )" +
            "AND (:result IS null OR s.result LIKE %:result% )" +
            "AND (:wasCollected IS null OR s.wasCollected LIKE %:wasCollected% )" +
            "AND (:wasStored IS null OR s.wasStored LIKE %:wasStored% )" +
            "AND (:comments IS null OR s.comments LIKE %:comments%)")
    List<Screening> advancedSearch(@Param("date") String date, @Param("name") String name, @Param("category") String category, @Param("creator") String creator, @Param("grid") String grid, @Param("result") String result, @Param("wasCollected") String wasCollected, @Param("wasStored") String wasStored, @Param("comments") String comments);
}
