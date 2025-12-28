package com.msd.utrip.entity;

import com.msd.utrip.entity.base.BaseEntity;
import com.msd.utrip.entity.field.MultiLanguageText;
import com.msd.utrip.entity.user.AgencyEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
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
@Table(name = "tours")
public class TourEntity extends BaseEntity {
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

  private String address;

  private Integer maxPeople;

  @Column(name = "price", precision = 8, scale = 2)
  private BigDecimal price;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "texts", column = @Column(name = "info", columnDefinition = "jsonb"))
  })
  private MultiLanguageText info;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(
        name = "texts",
        column = @Column(name = "additional_info", columnDefinition = "jsonb"))
  })
  private MultiLanguageText additionalInfo;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDate endDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "agency_id", updatable = false)
  private AgencyEntity agency;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private CategoryEntity category;

  @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
  private Set<ApplicationEntity> applications;
}
