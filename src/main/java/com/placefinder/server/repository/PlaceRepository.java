package com.placefinder.server.repository;

import com.placefinder.server.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query(value = "SELECT p FROM Place p WHERE (6371 * acos( cos( radians(?1) ) * cos( radians( p.latitude ) ) * cos( radians(p.longitude) - radians(?2)) + sin(radians(?1)) * sin( radians(p.latitude)))) < 100")
    List<Place> findAllAround(double latitude, double longitude);
}
