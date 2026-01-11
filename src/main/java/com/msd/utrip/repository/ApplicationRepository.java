package com.msd.utrip.repository;

import com.msd.utrip.entity.ApplicationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

  @Query("SELECT a FROM ApplicationEntity a LEFT JOIN FETCH a.tour t WHERE t.agency.id = ?1")
  Page<ApplicationEntity> findAllByAgencyId(Long agencyId, Pageable pageable);

  @Query(
      "SELECT a FROM ApplicationEntity a LEFT JOIN FETCH a.tour t WHERE t.agency.id = ?1 AND t.id = ?2")
  Page<ApplicationEntity> findAllByAgencyIdAndTourId(Long agencyId, Long tourId, Pageable pageable);
}
