package com.msd.utrip.controller.admin;

import com.msd.utrip.dto.request.admin.RegionRequest;
import com.msd.utrip.dto.response.admin.RegionAdminResponse;
import com.msd.utrip.service.admin.RegionAdminService;
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
@RequestMapping("/api/v1/admin/regions")
public class RegionAdminController {

  private final RegionAdminService regionAdminService;

  @GetMapping
  public ResponseEntity<Page<RegionAdminResponse>> list(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {

    Pageable pageable = PageRequest.of(page, size);

    Page<RegionAdminResponse> regions = regionAdminService.list(pageable);

    return ResponseEntity.ok(regions);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RegionAdminResponse> getOne(@PathVariable Long id) {

    RegionAdminResponse response = regionAdminService.getOne(id);

    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<RegionAdminResponse> create(@RequestBody @Valid RegionRequest request) {

    RegionAdminResponse created = regionAdminService.create(request);

    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<RegionAdminResponse> update(
      @PathVariable Long id, @RequestBody @Valid RegionRequest request) {

    RegionAdminResponse updated = regionAdminService.update(id, request);

    return ResponseEntity.ok(updated);
  }
}
