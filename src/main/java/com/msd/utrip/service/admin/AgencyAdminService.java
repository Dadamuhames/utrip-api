package com.msd.utrip.service.admin;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.dto.request.admin.AgencyRequest;
import com.msd.utrip.dto.request.admin.AgencyUpdateRequest;
import com.msd.utrip.dto.response.admin.AgencyAdminResponse;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.exception.AdminLoginExistsException;
import com.msd.utrip.exception.EntityNotFoundException;
import com.msd.utrip.mapper.AgencyMapper;
import com.msd.utrip.repository.agency.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AgencyAdminService {

  private final AgencyRepository agencyRepository;
  private final PasswordEncoder passwordEncoder;
  private final AgencyMapper agencyMapper;

  @Transactional(readOnly = true)
  public Page<AgencyAdminResponse> list(final Pageable pageable) {

    Page<AgencyEntity> agencies = agencyRepository.findAll(pageable);

    return agencies.map(agencyMapper::entityToAdminResponse);
  }

  private AgencyEntity getInstance(Long id) {
    return agencyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Transactional(readOnly = true)
  public AgencyAdminResponse getOne(Long id) {

    AgencyEntity agency = getInstance(id);

    return agencyMapper.entityToAdminResponse(agency);
  }

  @Transactional
  public AgencyAdminResponse create(AgencyRequest request) {
    validateAgencyRequest(request);

    String passwordEncoded = passwordEncoder.encode(request.password());

    AgencyEntity entity = agencyMapper.requestToAgency(request, passwordEncoded);

    entity = agencyRepository.saveAndFlush(entity);

    return agencyMapper.entityToAdminResponse(entity);
  }

  private void validateAgencyRequest(AgencyRequest request) {
    boolean existsByLogin = agencyRepository.existsByLogin(request.login());

    if (existsByLogin) {
      throw new AdminLoginExistsException(ErrorCode.LOGIN_EXISTS_CODE);
    }
  }

  @Transactional
  public AgencyAdminResponse update(Long id, AgencyUpdateRequest request) {
    validateAgencyUpdateRequest(id, request);

    AgencyEntity agency = getInstance(id);

    String password = agency.getPassword();

    if (request.password() != null) {
      password = passwordEncoder.encode(request.password());
    }

    agencyMapper.updateAgency(request, password, agency);

    agencyRepository.save(agency);

    return agencyMapper.entityToAdminResponse(agency);
  }

  private void validateAgencyUpdateRequest(Long id, AgencyUpdateRequest request) {
    boolean existsByLogin = agencyRepository.existsByLoginAndIdNot(request.login(), id);

    if (existsByLogin) {
      throw new AdminLoginExistsException(ErrorCode.LOGIN_EXISTS_CODE);
    }
  }
}
