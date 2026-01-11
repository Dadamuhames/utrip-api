package com.msd.utrip.entity;

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
@Table(name = "countries")
public class CountryEntity extends BaseEntity {
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "texts", column = @Column(name = "name", columnDefinition = "jsonb"))
  })
  private MultiLanguageText name;
}
