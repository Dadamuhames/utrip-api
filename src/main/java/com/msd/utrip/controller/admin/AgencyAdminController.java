package com.msd.utrip.controller.admin;

import com.msd.utrip.dto.request.admin.AgencyRequest;
import com.msd.utrip.dto.request.admin.AgencyUpdateRequest;
import com.msd.utrip.dto.response.admin.AgencyAdminResponse;
import com.msd.utrip.service.admin.AgencyAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/agencies")
public class AgencyAdminController {

  private final AgencyAdminService agencyAdminService;

  @GetMapping
  public ResponseEntity<Page<AgencyAdminResponse>> list(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize) {

    Pageable pageable = PageRequest.of(page, pageSize);

    Page<AgencyAdminResponse> agencies = agencyAdminService.list(pageable);

    return ResponseEntity.ok(agencies);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AgencyAdminResponse> one(@PathVariable Long id) {

    AgencyAdminResponse response = agencyAdminService.getOne(id);

    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<AgencyAdminResponse> create(@RequestBody @Valid AgencyRequest request) {

    AgencyAdminResponse created = agencyAdminService.create(request);

    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AgencyAdminResponse> update(
      @PathVariable Long id, @RequestBody @Valid AgencyUpdateRequest request) {

    AgencyAdminResponse updated = agencyAdminService.update(id, request);

    return ResponseEntity.ok(updated);
  }
}
