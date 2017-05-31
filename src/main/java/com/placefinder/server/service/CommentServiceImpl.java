package com.placefinder.server.service;

import com.placefinder.server.entity.Comment;
import com.placefinder.server.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository repository;

    public List<Comment> getAllForPlace(long placeID) {
        return repository.getAllForPlace(placeID);
        //throw new NotImplementedException();
        //return null;
    }

    public Comment save(Comment comment) {
        return repository.save(comment);
        //throw new NotImplementedException();
        //return null;
    }

    public void removeById(long id) {
        repository.delete(id);
        //throw new NotImplementedException();
    }

    public void deleteAllForPlace(long placeID) {
        repository.deleteAllForPlace(placeID);
    }
}
