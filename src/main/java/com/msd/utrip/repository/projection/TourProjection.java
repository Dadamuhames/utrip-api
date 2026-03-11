package com.msd.utrip.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourProjection {
  private Long id;
  private String title;
  private String address;
  private String image;
  private BigDecimal price;
  private LocalDate startDate;
  private LocalDate endDate;
  private Long agencyId;
  private String agencyName;
  private String agencyImage;
  private Double rating;
  private Integer reviewCount;
  private LocalDateTime createdAt;
}
