package com.msd.utrip.repository.agency;

import com.msd.utrip.entity.agency.AgencyVideoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyVideoRepository extends JpaRepository<AgencyVideoEntity, Long> {

  Page<AgencyVideoEntity> findAllByAgencyId(Long agencyId, Pageable pageable);
}
