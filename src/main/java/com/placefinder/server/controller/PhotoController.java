package com.placefinder.server.controller;

import com.placefinder.server.entity.Photo;
import com.placefinder.server.service.PhotoService;
import com.placefinder.server.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    private Random random = new Random();

    @Autowired
    private PhotoService photoService;

    @Autowired
    private PlaceService placeService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Photo>> getAllForPlace(@RequestParam("placeId") long placeID) {
        List<Photo> photos = photoService.getAllForPlace(placeID);
        if (photos != null)
            return new ResponseEntity<List<Photo>>(photos, HttpStatus.OK);
        else
            return new ResponseEntity<List<Photo>>(photos, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Photo> savePlace(@RequestBody Photo photo, @RequestParam("placeId") long placeID) {
        if (photo == null)
            return new ResponseEntity<Photo>(photo, HttpStatus.BAD_REQUEST);

        List<Photo> allPhotos = photoService.getAllForPlace(placeID);
        int filename;
        boolean uniqueFilename;
        do {
            uniqueFilename = true;
            filename = random.nextInt();
            for (Photo p : allPhotos) {
                if (p.getFilename().equals(String.valueOf(filename))) {
                    uniqueFilename = false;
                    break;
                }
            }
        } while (!uniqueFilename);

        photo.setFilename(String.valueOf(filename));
        photo.setPlace(placeService.findById(placeID));

        Photo savedPhoto = photoService.save(photo);
        if (savedPhoto != null) {
            return new ResponseEntity<Photo>(savedPhoto, HttpStatus.CREATED);
        } else
            return new ResponseEntity<Photo>(savedPhoto, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deletePhoto(@PathVariable("id") long photoID) {
        photoService.removeById(photoID);
        //notificationService.notifyPlaceDeleted(placeID);
    }
}
