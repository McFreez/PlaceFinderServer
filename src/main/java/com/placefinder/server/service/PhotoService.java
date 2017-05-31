package com.placefinder.server.service;


import com.placefinder.server.entity.Photo;

import java.util.List;

public interface PhotoService {

    List<Photo> getAll();
    List<Photo> getAllForPlace(long placeID);
    Photo save(Photo photo);
    void removeById(long id);
    void deleteAllForPlace(long placeID);
}
