package com.placefinder.server.repository;


import com.placefinder.server.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT c FROM Comment c WHERE place_id = ?1")
    List<Comment> getAllForPlace(long placeID);
}
