package com.msd.utrip.entity;

import com.msd.utrip.constant.enums.ApplicationStatus;
import com.msd.utrip.entity.base.BaseEntity;
import com.msd.utrip.entity.tour.TourEntity;
import com.msd.utrip.entity.user.UserEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "applications")
public class ApplicationEntity extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tour_id", updatable = false, nullable = false)
  private TourEntity tour;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", updatable = false, nullable = false)
  private UserEntity user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Builder.Default
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private ApplicationStatus status = ApplicationStatus.NEW;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private Integer personCount;

  @Column(nullable = false)
  private BigDecimal totalPrice;
}
