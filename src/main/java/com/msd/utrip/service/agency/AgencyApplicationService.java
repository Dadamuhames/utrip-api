package com.msd.utrip.service.agency;

import com.msd.utrip.dto.request.ApplicationFilter;
import com.msd.utrip.dto.response.ApplicationResponse;
import com.msd.utrip.entity.ApplicationEntity;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.mapper.ApplicationMapper;
import com.msd.utrip.repository.ApplicationFilterRepository;
import com.msd.utrip.service.LanguageExtractService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AgencyApplicationService {
  private final ApplicationFilterRepository applicationFilterRepository;
  private final LanguageExtractService languageExtractService;
  private final ApplicationMapper applicationMapper;

  @Transactional(readOnly = true)
  public Page<ApplicationResponse> list(
      final ApplicationFilter filter, final AgencyEntity agency, final Pageable pageable) {

    String lang = languageExtractService.getCurrentLanguage();

    Page<ApplicationEntity> applications =
        applicationFilterRepository.finaAllByAgency(filter, agency.getId(), pageable);

    return applications.map(a -> applicationMapper.entityToResponse(a, lang));
  }
}
