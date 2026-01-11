package com.msd.utrip.entity.agency;

import com.msd.utrip.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agency_videos")
public class AgencyVideoEntity extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "agency_id", updatable = false, nullable = false)
  private AgencyEntity agency;

  @Column(nullable = false)
  private String video;
}
