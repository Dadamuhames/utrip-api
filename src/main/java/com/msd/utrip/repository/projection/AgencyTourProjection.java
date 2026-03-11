package com.msd.utrip.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgencyTourProjection {
  private Long id;
  private String title;
  private String address;
  private String image;
  private BigDecimal price;
  private LocalDate startDate;
  private LocalDate endDate;
  private Integer applicationCount;
  private LocalDateTime createdAt;
}
