package com.msd.utrip.repository.tour;

import com.msd.utrip.entity.tour.InclusionEntity;
import com.msd.utrip.repository.projection.InclusionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InclusionsRepository extends JpaRepository<InclusionEntity, Long> {

    @Query(
        value = "SELECT i.id AS id, " +
            "COALESCE(i.title->>:lang, i.title->>'ru', '') AS title, " +
            "i.is_included AS is_included " +
            "FROM postgres.inclusions i WHERE i.tour_id = :tourId",
        nativeQuery = true)
    List<InclusionProjection> findByTourIdLocalized(
        @Param("tourId") Long tourId,
        @Param("lang") String lang);
}