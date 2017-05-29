package com.placefinder.server.repository;


import com.placefinder.server.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Query(value = "SELECT p FROM Photo p WHERE place_id = ?1")
    List<Photo> getAllForPlace(long placeID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Photo p WHERE place_id = ?1")
    void deleteAllForPlace(long placeID);
}
