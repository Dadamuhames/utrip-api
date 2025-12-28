package com.msd.utrip.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TourDetailProjection {

  Long getId();

  String getTitle();

  String getAddress();

  LocalDate getDateFrom();

  LocalDate getDateTo();

  Integer getMaxPlaces();

  Integer getPlacesLeft();

  BigDecimal getPrice();

  String getInfo();

  String getAdditionalInfo();

  Long getAgencyId();

  String getAgencyName();

  String getAgencyImage();

  Double getRating();

  Integer getReviewCount();
}
