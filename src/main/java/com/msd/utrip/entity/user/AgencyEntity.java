package com.msd.utrip.entity.user;

import com.msd.utrip.constant.enums.Role;
import com.msd.utrip.entity.ReviewEntity;
import com.msd.utrip.entity.base.BaseDeactivatableEntity;
import com.msd.utrip.entity.base.CustomUserDetails;
import com.msd.utrip.entity.field.MultiLanguageText;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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


  @AttributeOverrides({
    @AttributeOverride(name = "texts", column = @Column(name = "info", columnDefinition = "jsonb"))
  })
  private MultiLanguageText info;

  @OneToMany(mappedBy = "agency", fetch = FetchType.LAZY)
  private Set<ReviewEntity> reviews;

  @Override
  public Role getUserRole() {
    return Role.USER;
  }

  @Override
  public String getUsername() {
    return login;
  }
}
