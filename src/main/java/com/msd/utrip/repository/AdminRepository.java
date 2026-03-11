package com.msd.utrip.repository;

import com.msd.utrip.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {


    Optional<AdminEntity> findByLogin(String login);

}
