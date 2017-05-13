package com.placefinder.server.service;


import com.placefinder.server.entity.Place;

import java.util.List;

public interface PlaceService {

    List<Place> findAll();
    Place findById(long id);
    Place save(Place place);
    void removeById(long id);
    List<Place> findAllAround(double latitude, double longitude);
}
