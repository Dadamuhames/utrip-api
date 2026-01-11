package com.msd.utrip.entity.tour;

import com.msd.utrip.entity.base.BaseEntity;
import com.msd.utrip.entity.field.MultiLanguageText;
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
@Table(name = "schedules", indexes = @Index(columnList = "tour_id"))
public class ScheduleEntity extends BaseEntity {

  @Column(nullable = false)
  private Integer dayNumber;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "texts", column = @Column(name = "title", columnDefinition = "jsonb"))
  })
  private MultiLanguageText title;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(
        name = "texts",
        column = @Column(name = "subtitle", columnDefinition = "jsonb"))
  })
  private MultiLanguageText subtitle;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tour_id", updatable = false, nullable = false)
  private TourEntity tour;
}
