package com.msd.utrip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class AppConfig {

  @Bean
  protected SecureRandom secureRandom() {
    return new SecureRandom();
  }
}
