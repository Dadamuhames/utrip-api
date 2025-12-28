package com.msd.utrip.dto.request;

import jakarta.validation.constraints.AssertTrue;

public record ReviewFilterRequest(Long agencyId, Long tourId) {

  @AssertTrue(message = "agencyId or tourId required, but not both")
  private boolean isFilteredBYAgencyOrTour() {
    return agencyId != null ^ tourId != null;
  }
}
