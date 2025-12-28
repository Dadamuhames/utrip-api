package com.msd.utrip.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TourProjection {
    Long getId();

    String getTitle();

    String getAddress();

    String getImage();

    BigDecimal getPrice();

    LocalDate getStartDate();

    LocalDate getEndDate();

    Long getAgencyId();

    String getAgencyName();

    String getAgencyImage();

    Double getRating();

    Integer getReviewCount();
}
