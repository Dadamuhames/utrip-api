package com.msd.utrip.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public interface AgencyTourProjection {
  Long getId();

  String getTitle();

  String getAddress();

  String getImage();

  BigDecimal getPrice();

  LocalDate getStartDate();

  LocalDate getEndDate();

  Integer getApplicationCount();

  OffsetDateTime getCreatedAt();
}
