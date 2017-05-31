package com.placefinder.server.service;


import com.placefinder.server.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllForPlace(long placeID);
    Comment save(Comment comment);
    void removeById(long id);
    void deleteAllForPlace(long placeID);
}
