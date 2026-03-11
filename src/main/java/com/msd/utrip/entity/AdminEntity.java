package com.msd.utrip.entity;


import com.msd.utrip.constant.enums.Role;
import com.msd.utrip.entity.base.BaseDeactivatableEntity;
import com.msd.utrip.entity.base.BaseEntity;
import com.msd.utrip.entity.base.CustomUserDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admins")
public class AdminEntity extends BaseDeactivatableEntity implements CustomUserDetails {

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;


    @Override
    public Role getUserRole() {
        return Role.ADMIN;
    }

    @Override
    public String getUsername() {
        return login;
    }
}
