package com.msd.utrip.entity;

import com.msd.utrip.entity.base.BaseEntity;
import com.msd.utrip.entity.user.AgencyEntity;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "agency_images")
public class AgencyImageEntity extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "agency_id", updatable = false, nullable = false)
  private AgencyEntity agency;

  @Column(nullable = false)
  private String image;
}
