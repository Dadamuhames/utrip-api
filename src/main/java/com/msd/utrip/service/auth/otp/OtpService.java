package com.msd.utrip.service.auth.otp;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.entity.redis.OtpEntity;
import com.msd.utrip.exception.OtpInvalidException;
import com.msd.utrip.repository.redis.OtpRepository;
import com.msd.utrip.utils.OtpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpService {
  private final OtpUtils otpUtils;
  private final OtpRepository otpRepository;
  private final PasswordEncoder passwordEncoder;

  public String createOtp(final Long telegramId, final String phone) {
    String otp = otpUtils.generateOtp();

    otpRepository.findByPhone(phone).ifPresent(otpRepository::delete);

    OtpEntity otpEntity =
        OtpEntity.builder()
            .otpHash(passwordEncoder.encode(otp))
            .telegramId(telegramId)
            .phone(phone)
            .build();

    otpRepository.save(otpEntity);

    return otp;
  }

  public OtpEntity getAndValidateOtp(final String otpCode, final String phone) {
    OtpEntity otp = otpRepository.findByPhone(phone).orElse(null);

    if (!isOtpValid(otp, otpCode)) throw new OtpInvalidException(ErrorCode.OTP_EXPIRED_CODE);

    return otp;
  }

  public boolean isOtpValid(final OtpEntity otp, final String otpCode) {
    return otp != null && passwordEncoder.matches(otpCode, otp.getOtpHash());
  }
}
