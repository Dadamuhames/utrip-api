package com.msd.utrip.repository;

import com.msd.utrip.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

  @Query(
      "SELECT r FROM ReviewEntity r "
          + "LEFT JOIN TourEntity t ON t.id = :tourId "
          + "LEFT JOIN FETCH r.user u "
          + "WHERE r.agency.id = t.agency.id ")
  Page<ReviewEntity> findAllByTourId(@Param("tourId") Long tourId, Pageable pageable);

  @Query(
      "SELECT r FROM ReviewEntity r "
          + "LEFT JOIN FETCH r.user u "
          + "WHERE r.agency.id = :agencyId")
  Page<ReviewEntity> findAllByAgencyId(@Param("agencyId") Long agencyId, Pageable pageable);
}
