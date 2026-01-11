package com.msd.utrip.repository.agency;

import com.msd.utrip.entity.agency.AgencyImageEntity;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyImageRepository extends JpaRepository<AgencyImageEntity, Long> {

  Page<AgencyImageEntity> findAllByAgencyId(Long agencyId, Pageable pageable);
}
