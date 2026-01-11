package com.msd.utrip.entity.tour;

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
@Table(name = "tour_images", indexes = @Index(columnList = "tour_id"))
public class TourImageEntity extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tour_id", updatable = false, nullable = false)
  private TourEntity tour;

  @Column(nullable = false)
  private String image;
}
