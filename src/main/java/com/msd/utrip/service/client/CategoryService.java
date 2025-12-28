package com.msd.utrip.service.client;

import com.msd.utrip.dto.response.CategoryResponse;
import com.msd.utrip.mapper.CategoryMapper;
import com.msd.utrip.repository.CategoryRepository;
import com.msd.utrip.repository.projection.CategoryProjection;
import com.msd.utrip.service.LanguageExtractService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final LanguageExtractService languageExtractService;
  private final CategoryMapper categoryMapper;

  public Page<CategoryResponse> list(final Pageable pageable) {
    String lang = languageExtractService.getCurrentLanguage();

    Page<CategoryProjection> categories = categoryRepository.findAllLocalized(lang, pageable);

    return categories.map(categoryMapper::projectionToResponse);
  }
}
