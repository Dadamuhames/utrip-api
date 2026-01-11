package com.msd.utrip.entity.agency;

import com.msd.utrip.entity.base.BaseEntity;
import com.msd.utrip.entity.field.MultiLanguageText;
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
@Table(name = "agency_infos")
public class AgencyInfoEntity extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "agency_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private AgencyEntity agency;

  @AttributeOverrides({
    @AttributeOverride(name = "texts", column = @Column(name = "info", columnDefinition = "jsonb"))
  })
  private MultiLanguageText info;

  private String address;

  private String phone;

  private String email;
}
