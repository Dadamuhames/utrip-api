package com.msd.utrip.service.agency;


import com.msd.utrip.dto.response.tour.TourResponse;
import com.msd.utrip.repository.tour.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgencyTourService {
    private final TourRepository tourRepository;



}
