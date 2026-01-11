package com.msd.utrip.repository.tour;

import com.msd.utrip.entity.tour.TourEntity;
import java.util.Optional;

import com.msd.utrip.repository.projection.TourDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TourRepository extends JpaRepository<TourEntity, Long> {

  @Query(
      value =
          "SELECT t.id AS id, "
              + "COALESCE(t.title->>:lang, t.title->>'ru', '') AS title, "
              + "t.address AS address, "
              + "t.date_from AS date_from, "
              + "t.date_to AS date_to, "
              + "t.max_people AS max_places, "
              + "t.max_people - COALESCE(COUNT(DISTINCT app.id), 0) AS places_left, "
              + "t.price AS price, "
              + "COALESCE(t.info->>:lang, t.info->>'ru', '') AS info, "
              + "COALESCE(t.additional_info->>:lang, t.additional_info->>'ru', '') AS additional_info, "
              + "a.id AS agency_id, "
              + "a.name AS agency_name, "
              + "a.image AS agency_image, "
              + "COALESCE(AVG(r.rating), 0.0) AS rating, "
              + "COUNT(DISTINCT r.id) AS review_count "
              + "FROM postgres.tours t "
              + "LEFT JOIN postgres.agencies a ON a.id = t.agency_id "
              + "LEFT JOIN postgres.reviews r ON r.agency_id = a.id "
              + "LEFT JOIN postgres.applications app ON app.tour_id = t.id "
              + "WHERE t.id = :id "
              + "GROUP BY t.id, t.title, t.address, t.date_from, t.date_to, t.max_people, "
              + "t.price, t.info, t.additional_info, a.id, a.name, a.image",
      nativeQuery = true)
  Optional<TourDetailProjection> findByIdLocalized(
      @Param("id") Long id, @Param("lang") String lang);

  @Query(
      value =
          "SELECT t.id AS id, "
              + "COALESCE(t.title->>:lang, t.title->>'ru', '') AS title, "
              + "t.address AS address, "
              + "t.date_from AS date_from, "
              + "t.date_to AS date_to, "
              + "t.max_people AS max_places, "
              + "t.max_people - COALESCE(COUNT(DISTINCT app.id), 0) AS places_left, "
              + "t.price AS price, "
              + "COALESCE(t.info->>:lang, t.info->>'ru', '') AS info, "
              + "COALESCE(t.additional_info->>:lang, t.additional_info->>'ru', '') AS additional_info, "
              + "COALESCE(AVG(r.rating), 0.0) AS rating, "
              + "COUNT(DISTINCT r.id) AS review_count "
              + "FROM postgres.tours t "
              + "LEFT JOIN postgres.reviews r ON r.agency_id = a.id "
              + "LEFT JOIN postgres.applications app ON app.tour_id = t.id "
              + "WHERE t.id = :id AND t.agency_id = :agency_id "
              + "GROUP BY t.id, t.title, t.address, t.date_from, t.date_to, t.max_people, "
              + "t.price, t.info, t.additional_info, a.id, a.name, a.image",
      nativeQuery = true)
  Optional<TourDetailProjection> findByIdLocalizedForAgency(
      @Param("id") Long id, @Param("agencyId") Long agencyId, @Param("lang") String lang);

  @Query(
      value =
          "SELECT t.* FROM postgres.tours t "
              + "LEFT JOIN postgres.applications a ON a.tour_id = t.id "
              + "WHERE t.id = :id "
              + "AND t.date_from > CURRENT_DATE "
              + "GROUP BY t.id "
              + "HAVING COALESCE(COUNT(a.id), 0) + :personCount <= t.max_people",
      nativeQuery = true)
  Optional<TourEntity> findByIdActive(
      @Param("id") Long id, @Param("personCount") Integer personCount);

  @Query(
      "SELECT COUNT(t) > 0 FROM TourEntity t "
          + "INNER JOIN ApplicationEntity a ON a.tour.id = t.id AND a.user.id = :userId  "
          + "WHERE t.agency.id = :agencyId AND t.endDate <= CURRENT_DATE")
  boolean existsByAgencyIdAndUserIdAndCompleted(
      @Param("agencyId") Long agencyId, @Param("userId") Long userId);
}
