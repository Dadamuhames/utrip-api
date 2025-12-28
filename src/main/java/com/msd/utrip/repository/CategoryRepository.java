package com.msd.utrip.repository;

import com.msd.utrip.entity.CategoryEntity;
import com.msd.utrip.repository.projection.CategoryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query(
        value = "SELECT c.id AS id, " +
            "COALESCE(c.title->>:lang, c.title->>'ru', '') AS title, " +
            "c.image AS image " +
            "FROM postgres.categories c",
        countQuery = "SELECT COUNT(*) FROM categories c",
        nativeQuery = true)
    Page<CategoryProjection> findAllLocalized(@Param("lang") String lang, Pageable pageable);
}