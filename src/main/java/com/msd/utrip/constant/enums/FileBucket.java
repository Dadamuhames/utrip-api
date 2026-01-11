package com.msd.utrip.constant.enums;

import lombok.Getter;

@Getter
public enum FileBucket {
  TOUR_IMAGE("tour-images"),
  TOUR_VIDEOS("tour-videos"),
  AGENCY_IMAGE("agency-images"),
  AGENCY_VIDEO("agency_videos"),
  USER("user-images");

  final String bucket;

  FileBucket(String bucket) {
    this.bucket = bucket;
  }
}
