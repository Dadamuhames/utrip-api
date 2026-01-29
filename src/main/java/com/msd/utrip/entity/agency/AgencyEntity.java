package com.msd.utrip.entity.agency;

import com.msd.utrip.constant.enums.Role;
import com.msd.utrip.entity.ReviewEntity;
import com.msd.utrip.entity.base.BaseDeactivatableEntity;
import com.msd.utrip.entity.base.CustomUserDetails;
import com.msd.utrip.entity.field.MultiLanguageText;
import jakarta.persistence.*;
import java.util.Set;
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
@Table(name = "agencies")
public class AgencyEntity extends BaseDeactivatableEntity implements CustomUserDetails {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String login;

  @Column(nullable = false)
  private String password;

  private String image;

  @AttributeOverrides({
      @AttributeOverride(name = "texts", column = @Column(name = "subtitle", columnDefinition = "jsonb"))
  })
  private MultiLanguageText subtitle;

  @OneToMany(mappedBy = "agency", fetch = FetchType.LAZY)
  private Set<ReviewEntity> reviews;

  @Override
  public Role getUserRole() {
    return Role.AGENCY;
  }

  @Override
  public String getUsername() {
    return login;
  }
}
