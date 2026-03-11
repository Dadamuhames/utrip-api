package com.msd.utrip.service.admin;

import com.msd.utrip.dto.request.admin.CategoryRequest;
import com.msd.utrip.dto.response.admin.CategoryAdminResponse;
import com.msd.utrip.entity.CategoryEntity;
import com.msd.utrip.exception.EntityNotFoundException;
import com.msd.utrip.mapper.CategoryMapper;
import com.msd.utrip.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryAdminService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  @Transactional(readOnly = true)
  public Page<CategoryAdminResponse> list(Pageable pageable) {

    Page<CategoryEntity> categories = categoryRepository.findAll(pageable);

    return categories.map(categoryMapper::entityToAdminResponse);
  }

  private CategoryEntity getInstance(Long id) {
    return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Transactional(readOnly = true)
  public CategoryAdminResponse getOne(Long id) {

    CategoryEntity entity = getInstance(id);

    return categoryMapper.entityToAdminResponse(entity);
  }

  @Transactional
  public CategoryAdminResponse create(CategoryRequest request) {

    CategoryEntity category = categoryMapper.requestToEntity(request);

    category = categoryRepository.save(category);

    return categoryMapper.entityToAdminResponse(category);
  }

  @Transactional
  public CategoryAdminResponse update(Long id, CategoryRequest request) {

    CategoryEntity category = getInstance(id);

    categoryMapper.updateEntity(request, category);

    categoryRepository.save(category);

    return categoryMapper.entityToAdminResponse(category);
  }
}
