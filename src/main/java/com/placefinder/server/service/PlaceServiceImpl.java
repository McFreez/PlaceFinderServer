package com.placefinder.server.service;

import com.placefinder.server.entity.Place;
import com.placefinder.server.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository repository;

    public List<Place> findAll() {
        return repository.findAll();
    }

    public Place findById(long id) {
        return repository.findOne(id);
    }

    public Place save(Place place) {
        return repository.saveAndFlush(place);
    }

    public void removeById(long id) {
        repository.delete(id);
    }

    public List<Place> findAllAround(double latitude, double longitude) {
        return repository.findAllAround(latitude, longitude);
    }
}
