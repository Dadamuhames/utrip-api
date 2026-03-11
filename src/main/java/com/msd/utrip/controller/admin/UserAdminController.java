package com.msd.utrip.controller.admin;

import com.msd.utrip.dto.response.admin.UserAdminResponse;
import com.msd.utrip.service.admin.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/users")
public class UserAdminController {

  private final UserAdminService userAdminService;

  @GetMapping
  public ResponseEntity<Page<UserAdminResponse>> list(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {

    Pageable pageable = PageRequest.of(page, size);

    Page<UserAdminResponse> users = userAdminService.list(pageable);

    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserAdminResponse> getOne(@PathVariable Long id) {

    UserAdminResponse response = userAdminService.getOne(id);

    return ResponseEntity.ok(response);
  }
}
