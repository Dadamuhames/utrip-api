package com.msd.utrip.entity.tour;

import com.msd.utrip.entity.base.BaseEntity;
import com.msd.utrip.entity.field.MultiLanguageText;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "inclusions", indexes = @Index(columnList = "tour_id"))
public class InclusionEntity extends BaseEntity {
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "texts", column = @Column(name = "title", columnDefinition = "jsonb"))
  })
  private MultiLanguageText title;

  @Builder.Default private boolean isIncluded = true;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tour_id", updatable = false, nullable = false)
  private TourEntity tour;
}
