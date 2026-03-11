package com.msd.utrip.controller.admin;

import com.msd.utrip.dto.request.admin.CountryRequest;
import com.msd.utrip.dto.response.admin.CountryAdminResponse;
import com.msd.utrip.service.admin.CountryAdminService;
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
@RequestMapping("/api/v1/admin/countries")
public class CountryAdminController {

  private final CountryAdminService countryAdminService;

  @GetMapping
  public ResponseEntity<Page<CountryAdminResponse>> list(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {

    Pageable pageable = PageRequest.of(page, size);

    Page<CountryAdminResponse> countries = countryAdminService.list(pageable);

    return ResponseEntity.ok(countries);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CountryAdminResponse> getOne(@PathVariable Long id) {
    CountryAdminResponse response = countryAdminService.getOne(id);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<CountryAdminResponse> create(@RequestBody @Valid CountryRequest request) {

    CountryAdminResponse created = countryAdminService.create(request);

    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CountryAdminResponse> update(
      @PathVariable Long id, @RequestBody @Valid CountryRequest request) {

    CountryAdminResponse updated = countryAdminService.update(id, request);

    return ResponseEntity.ok(updated);
  }
}
