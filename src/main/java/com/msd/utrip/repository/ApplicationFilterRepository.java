package com.msd.utrip.repository;

import com.msd.utrip.dto.WhereResult;
import com.msd.utrip.dto.request.ApplicationFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.msd.utrip.repository.projection.ApplicationProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApplicationFilterRepository {
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private final String query =
      "SELECT a.id AS id, a.full_name AS fullName, a.phone AS phone, a.person_count AS personCount, a.created_at AS createdAt, "
          + "t.id AS tourId, COALESCE(t.title->>:lang, t.title->>'ru', '') AS title, t.start_date AS startDate, t.end_date AS endDate "
          + "FROM postgres.applications a "
          + "LEFT JOIN postgres.tours t ON t.id = a.tour_id ";

  private final String pagination = " ORDER BY t.id LIMIT :limit OFFSET :offset";

  private String getFilterQuery(String whereClause) {
    return query + whereClause + pagination;
  }

  private String getCountQuery(String whereClause) {
    return """
            SELECT COUNT(DISTINCT a.id)
            FROM postgres.applications a
            LEFT JOIN postgres.tours t ON t.id = a.tour_id
            """
        + whereClause;
  }

  private WhereResult getWhereClause(ApplicationFilter request, Long agencyId) {
    StringBuilder where = new StringBuilder(" WHERE t.agency_id = :agencyId ");

    Map<String, Object> params = new HashMap<>();
    params.put("agencyId", agencyId);

    if (request.tourId() != null) {
      params.put("tourId", request.categoryId());
      where.append(" AND t.id = :tourId");
    }

    if (request.search() != null && !request.search().isBlank()) {
      params.put("search", "%" + request.search().toLowerCase() + "%");
      where.append(" AND LOWER(COALESCE(t.title->>:lang, t.title->>'ru', '')) LIKE %:search%");
    }

    if (request.categoryId() != null) {
      params.put("categoryId", request.categoryId());
      where.append(" AND t.category_id = :categoryId");
    }

    if (!request.isActive()) {
      where.append(" AND t.end_date < CURRENT_DATE");
    } else {
      where.append(" AND t.end_date > CURRENT_DATE");
    }

    return new WhereResult(where.toString(), params);
  }

  public Page<ApplicationProjection> finaAllByAgency(
      String lang, ApplicationFilter request, Long agencyId, Pageable pageable) {

    WhereResult whereResult = getWhereClause(request, agencyId);

    Map<String, Object> params = new HashMap<>(whereResult.params());

    params.put("limit", pageable.getPageSize());
    params.put("offset", pageable.getOffset());
    params.put("lang", lang);

    String query = getFilterQuery(whereResult.whereClause());

    List<ApplicationProjection> applications =
        namedParameterJdbcTemplate.query(
            query, params, BeanPropertyRowMapper.newInstance(ApplicationProjection.class));

    String countQuery = getCountQuery(whereResult.whereClause());

    Long total = namedParameterJdbcTemplate.queryForObject(countQuery, params, Long.class);

    if (total == null) total = 0L;

    return new PageImpl<>(applications, pageable, total);
  }
}
