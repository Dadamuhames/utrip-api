package com.msd.utrip.entity;

import com.msd.utrip.constant.enums.Role;
import jakarta.persistence.*;
import java.time.Instant;
import lombok.AllArgsConstructor;
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
@Table(name = "refresh_token_entity")
public class RefreshTokenEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String token;

  @Column(nullable = false)
  private Instant expiryDate;

  @Column(nullable = false)
  private String subject;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "user_role")
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private Role userRole;
}
