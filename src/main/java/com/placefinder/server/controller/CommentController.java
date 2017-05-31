package com.placefinder.server.controller;

import com.placefinder.server.entity.Comment;
import com.placefinder.server.service.CommentService;
import com.placefinder.server.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private PlaceService placeService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Comment>> getAllForPlace(@RequestParam("placeId") long placeID){
        List<Comment> comments = commentService.getAllForPlace(placeID);
        if(comments != null)
            if(comments.size() > 0)
                return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
            else
                return new ResponseEntity<List<Comment>>(comments, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<List<Comment>>(comments, HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Comment> savePlace(@RequestBody Comment comment, @RequestParam("placeId") long placeID){
        if(comment == null)
            return new ResponseEntity<Comment>(comment, HttpStatus.BAD_REQUEST);

        comment.setPlace(placeService.findById(placeID));

        Comment savedComment = commentService.save(comment);
        if(savedComment != null) {
            //notificationService.notifyPlaceAdded(savedPlace);
            return new ResponseEntity<Comment>(savedComment, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<Comment>(savedComment, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deletePhoto(@PathVariable("id") long photoID){
        commentService.removeById(photoID);
        //notificationService.notifyPlaceDeleted(placeID);
    }
}
