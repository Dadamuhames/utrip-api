package com.msd.utrip;

import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.entity.field.MultiLanguageText;
import com.msd.utrip.repository.agency.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class UtripApplication implements CommandLineRunner {
  private final AgencyRepository agencyRepository;
  private final PasswordEncoder passwordEncoder;

  public static void main(String[] args) {
    SpringApplication.run(UtripApplication.class, args);
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    boolean agencyExists = agencyRepository.existsById(1L);

    if (!agencyExists) {
      AgencyEntity admin =
          AgencyEntity.builder()
              .name("test")
              .subtitle(
                  new MultiLanguageText(Map.of("ru", "test", "en", "test ru", "uz", "test uz")))
              .login("test")
              .password(passwordEncoder.encode("123123"))
              .isActive(true)
              .build();

      agencyRepository.save(admin);
    }
  }
}
