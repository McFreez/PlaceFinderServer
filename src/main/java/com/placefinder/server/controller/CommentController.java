package com.placefinder.server.controller;

import com.placefinder.server.entity.Comment;
import com.placefinder.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Comment>> getAllForPlace(@RequestParam("placeId") long placeID){
        return new ResponseEntity<List<Comment>>(service.getAllForPlace(placeID), HttpStatus.OK);
    }
}
