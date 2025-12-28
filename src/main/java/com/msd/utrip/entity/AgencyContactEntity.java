package com.msd.utrip.entity;

import com.msd.utrip.entity.base.BaseEntity;
import com.msd.utrip.entity.user.AgencyEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agency_contacts")
public class AgencyContactEntity extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "agency_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private AgencyEntity agency;

  private String address;

  private String phone;

  private String email;
}
