package com.msd.utrip.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationProjection {

  private Long id;

  private String fullName;

  private String phone;

  private Integer personCount;

  private LocalDateTime createdAt;

  private Long tourId;

  private String title;

  private LocalDate startDate;

    private LocalDate endDate;
}
