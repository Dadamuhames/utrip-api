package com.msd.utrip.repository;

import com.msd.utrip.entity.AgencyImageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyImageRepository extends JpaRepository<AgencyImageEntity, Long> {

  List<AgencyImageEntity> findAllByAgencyId(Long agencyId);
}
