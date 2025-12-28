package com.msd.utrip.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.msd.utrip.constant.enums.Role;
import com.msd.utrip.service.auth.userdetails.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDetailsConfig {

  @Bean(name = "customUserDetailServices")
  public Map<Role, CustomUserDetailsService> customUserDetailServices(
      List<CustomUserDetailsService> detailsServices) {
    Map<Role, CustomUserDetailsService> detailServiceMap = new HashMap<>();

    for (var detailsService : detailsServices) {
      detailServiceMap.put(detailsService.getSupportedRole(), detailsService);
    }

    return detailServiceMap;
  }
}
