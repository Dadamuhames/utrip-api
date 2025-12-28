package com.msd.utrip.repository.projection;

public interface AgencyDetailProjection {
  Long getId();

  String getName();

  String getImage();

  String getSubtitle();

  String getInfo();

  String getAddress();

  String getPhone();

  String getEmail();

  Integer getActiveToursCount();

  Integer getFinishedToursCount();

  Integer getTotalParticipantsCount();
}
