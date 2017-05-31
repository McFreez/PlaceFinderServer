package com.placefinder.server.controller;

import com.placefinder.server.entity.Place;
import com.placefinder.server.service.CommentService;
import com.placefinder.server.service.NotificationService;
import com.placefinder.server.service.PhotoService;
import com.placefinder.server.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private CommentService commentService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Place>> getAllPlaces(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<List<Place>>(placeService.findAll(),responseHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Place> getPlaceByID(@PathVariable("id") long placeID){
        Place place = placeService.findById(placeID);
        if(place == null)
            return new ResponseEntity<Place>(place, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Place>(place, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Place> savePlace(@RequestBody Place place){
        Place savedPlace = placeService.save(place);
        if(savedPlace == place) {
            notificationService.notifyPlaceAdded(savedPlace);
            return new ResponseEntity<Place>(savedPlace, HttpStatus.CREATED);
        }
        else
        if(savedPlace == null)
            return new ResponseEntity<Place>(savedPlace, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<Place>(savedPlace, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deletePlace(@PathVariable("id") long placeID){
        try {
            photoService.deleteAllForPlace(placeID);
            commentService.deleteAllForPlace(placeID);

        }catch (Exception e){
            e.printStackTrace();
        }
        // TODO: delete all comments for place
        placeService.removeById(placeID);
        notificationService.notifyPlaceDeleted(placeID);


    }

    @GetMapping(value = "/allAround")
    @ResponseBody
    public ResponseEntity<List<Place>> getAllAroundUser(@RequestParam("lat") double latitude, @RequestParam("lng") double longitude){
        List<Place> selectedPlaces = placeService.findAllAround(latitude, longitude);
        if(selectedPlaces == null)
            return new ResponseEntity<List<Place>>(selectedPlaces, HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Place>>(selectedPlaces, HttpStatus.OK);
    }

}
