package com.msd.utrip.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageExtractService {
  private final HttpServletRequest request;

  public String getCurrentLanguage() {
    String lang = request.getHeader("Accept-Language");

    if (lang == null) return "uz";

    return lang;
  }
}
