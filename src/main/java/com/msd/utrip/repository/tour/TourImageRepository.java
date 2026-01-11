package com.msd.utrip.repository.tour;

import com.msd.utrip.entity.tour.TourImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourImageRepository extends JpaRepository<TourImageEntity, Long> {

    List<TourImageEntity> findByTourId(final Long id);

}
