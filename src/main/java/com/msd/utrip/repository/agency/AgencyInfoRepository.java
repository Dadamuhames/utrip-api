package com.msd.utrip.repository.agency;

import com.msd.utrip.entity.agency.AgencyInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgencyInfoRepository extends JpaRepository<AgencyInfoEntity, Long> {

    Optional<AgencyInfoEntity> findByAgencyId(final Long agencyId);

}
