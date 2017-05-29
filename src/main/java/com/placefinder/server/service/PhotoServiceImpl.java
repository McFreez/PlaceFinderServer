package com.placefinder.server.service;

import com.placefinder.server.entity.Photo;
import com.placefinder.server.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository repository;

    public List<Photo> getAll(){
        return repository.findAll();
    }

    public List<Photo> getAllForPlace(long placeID) {
        return repository.getAllForPlace(placeID);
        //throw new NotImplementedException();
        //return null;
    }

    public Photo save(Photo photo) {
        return repository.save(photo);
        //throw new NotImplementedException();
        //return null;
    }

    public void removeById(long id) {
        repository.delete(id);
    }

    public void deleteAllForPlace(long id) {
        repository.deleteAllForPlace(id);
    }
}
