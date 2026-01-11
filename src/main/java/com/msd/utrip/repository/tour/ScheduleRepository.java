package com.msd.utrip.repository.tour;

import com.msd.utrip.entity.tour.ScheduleEntity;
import com.msd.utrip.repository.projection.ScheduleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    @Query(
        value = "SELECT s.id AS id, " +
            "COALESCE(s.title->>:lang, s.title->>'ru', '') AS title, " +
            "COALESCE(s.subtitle->>:lang, s.subtitle->>'ru', '') AS subtitle " +
            "FROM schedules s WHERE s.tour_id = :tourId",
        nativeQuery = true)
    List<ScheduleProjection> findByTourIdLocalized(
        @Param("tourId") Long tourId,
        @Param("lang") String lang);
}