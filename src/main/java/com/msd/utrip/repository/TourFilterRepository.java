package com.msd.utrip.repository;

import com.msd.utrip.dto.request.TourFilterRequest;
import com.msd.utrip.repository.projection.TourProjection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TourFilterRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private final String queryStart =
      "SELECT "
          + "t.id AS id, "
          + "COALESCE(t.title->>:lang, t.title->>'ru', '') AS title, "
          + "t.address AS address, "
          + "i.image AS image, "
          + "t.price AS price, "
          + "t.start_date AS startDate, "
          + "t.end_date AS endDate, "
          + "a.id AS agencyId, "
          + "a.name AS agencyName, "
          + "a.image AS agencyImage, "
          + "COALESCE(AVG(r.rating), 0.0) AS rating, "
          + "COUNT(DISTINCT r.id) AS reviewCount "
          + "FROM postgres.tours t "
          + "LEFT JOIN postgres.agencies a ON a.id = t.agency_id "
          + "LEFT JOIN postgres.reviews r ON r.agency_id = a.id "
          + "LEFT JOIN LATERAL ( "
          + "SELECT i.image "
          + "FROM postgres.tour_images i "
          + "WHERE i.tour_id = t.id "
          + "ORDER BY i.id "
          + "LIMIT 1 "
          + ") i ON true";
  ;

  private final String groupBy =
      " GROUP BY t.id, t.title, t.address, t.price, t.start_date, t.end_date, a.id, a.name, a.image, i.image";

  private final String pagination = " ORDER BY t.id LIMIT :limit OFFSET :offset";

  private String getFilterQuery(String whereClause) {
    return queryStart + whereClause + groupBy + pagination;
  }

  private String getCountQuery(String whereClause) {
    return """
            SELECT COUNT(DISTINCT t.id)
            FROM postgres.tours t
            LEFT JOIN postgres.agencies a ON a.id = t.agency_id
            """
        + whereClause;
  }

  private WhereResult getWhereClause(String lang, TourFilterRequest request) {
    StringBuilder where =
        new StringBuilder(" WHERE t.end_date > CURRENT_DATE AND a.is_active = TRUE");
    Map<String, Object> params = new HashMap<>();
    params.put("lang", lang);

    if (request.search() != null && !request.search().isBlank()) {
      params.put("search", "%" + request.search().toLowerCase() + "%");
      where.append(" AND LOWER(COALESCE(t.title->>:lang, t.title->>'ru', '')) LIKE :search");
    }

    if (request.priceFrom() != null) {
      params.put("priceFrom", request.priceFrom());
      where.append(" AND t.price >= :priceFrom");
    }

    if (request.priceTo() != null) {
      params.put("priceTo", request.priceTo());
      where.append(" AND t.price < :priceTo");
    }

    if (request.categoryId() != null) {
      params.put("categoryId", request.categoryId());
      where.append(" AND t.category_id = :categoryId");
    }

    if (request.agencyId() != null) {
      params.put("agencyId", request.agencyId());
      where.append(" AND t.agency_id = :agencyId");
    }

    if (request.dateFrom() != null) {
      params.put("dateFrom", request.dateFrom());
      where.append(" AND t.start_date >= :dateFrom");
    }

    if (request.dateTo() != null) {
      params.put("dateTo", request.dateTo());
      where.append(" AND t.end_date < :dateTo");
    }

    return new WhereResult(where.toString(), params);
  }

  public Page<TourProjection> findAllLocalized(
      String lang, TourFilterRequest request, Pageable pageable) {
    WhereResult whereResult = getWhereClause(lang, request);
    Map<String, Object> params = new HashMap<>(whereResult.params());

    params.put("limit", pageable.getPageSize());
    params.put("offset", pageable.getOffset());

    String query = getFilterQuery(whereResult.whereClause());
    List<TourProjection> tours =
        namedParameterJdbcTemplate.query(
            query, params, BeanPropertyRowMapper.newInstance(TourProjection.class));

    String countQuery = getCountQuery(whereResult.whereClause());
    Long total = namedParameterJdbcTemplate.queryForObject(countQuery, params, Long.class);
    if (total == null) total = 0L;

    return new PageImpl<>(tours, pageable, total);
  }

  private record WhereResult(String whereClause, Map<String, Object> params) {}
}
