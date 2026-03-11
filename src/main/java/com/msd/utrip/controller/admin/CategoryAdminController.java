package com.msd.utrip.controller.admin;

import com.msd.utrip.dto.request.admin.CategoryRequest;
import com.msd.utrip.dto.response.admin.CategoryAdminResponse;
import com.msd.utrip.service.admin.CategoryAdminService;
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
@RequestMapping("/api/v1/admin/categories")
public class CategoryAdminController {

  private final CategoryAdminService categoryAdminService;

  @GetMapping
  public ResponseEntity<Page<CategoryAdminResponse>> list(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {

    Pageable pageable = PageRequest.of(page, size);

    Page<CategoryAdminResponse> categories = categoryAdminService.list(pageable);

    return ResponseEntity.ok(categories);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryAdminResponse> getOne(@PathVariable Long id) {

    CategoryAdminResponse response = categoryAdminService.getOne(id);

    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<CategoryAdminResponse> create(@RequestBody @Valid CategoryRequest request) {

    CategoryAdminResponse created = categoryAdminService.create(request);

    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryAdminResponse> update(
      @PathVariable Long id, @RequestBody @Valid CategoryRequest request) {

    CategoryAdminResponse updated = categoryAdminService.update(id, request);

    return ResponseEntity.ok(updated);
  }
}
