package com.msd.utrip.repository;

import com.msd.utrip.entity.user.AgencyEntity;
import com.msd.utrip.repository.projection.AgencyDetailProjection;
import com.msd.utrip.repository.projection.AgencyProjection;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgencyRepository extends JpaRepository<AgencyEntity, Long> {

  Optional<AgencyEntity> findByLogin(String login);

  @Query(
      value =
          "SELECT a.id AS id, a.name AS name, a.image AS image, "
              + "COALESCE(a.subtitle->>:lang, a.subtitle->>'ru', '') AS subtitle "
              + "FROM agencies a WHERE a.is_active = true",
      countQuery = "SELECT COUNT(*) FROM agencies a WHERE a.is_active = true",
      nativeQuery = true)
  Page<AgencyProjection> findAllActives(@Param("lang") String lang, Pageable pageable);

  @Query(
      value =
          "SELECT a.id AS id, a.name AS name, a.image AS image, "
              + "COALESCE(a.subtitle->>:lang, a.subtitle->>'ru', '') AS subtitle, "
              + "COALESCE(a.info->>:lang, a.info->>'ru', '') AS info, "
              + "c.address AS address, c.phone AS phone, c.email AS email, "
              + "COUNT(DISTINCT CASE WHEN t_a.start_date > CURRENT_DATE THEN t_a.id END) AS active_tours_count, "
              + "COUNT(DISTINCT CASE WHEN t_o.end_date < CURRENT_DATE THEN t_o.id END) AS finished_tours_count, "
              + "COUNT(DISTINCT apl.id) AS total_participants_count "
              + "FROM agencies a "
              + "LEFT JOIN agency_contacts c ON c.agency_id = a.id "
              + "LEFT JOIN tours t_a ON t_a.agency_id = a.id "
              + "LEFT JOIN tours t_o ON t_o.agency_id = a.id "
              + "LEFT JOIN applications apl ON apl.agency_id = a.id "
              + "WHERE a.id = :id "
              + "GROUP BY a.id, a.name, a.image, a.subtitle, a.info, c.address, c.phone, c.email",
      nativeQuery = true)
  Optional<AgencyDetailProjection> findByIdLocalized(
      @Param("id") Long id, @Param("lang") String lang);
}
