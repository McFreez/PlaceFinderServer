package com.placefinder.server.controller;

import com.placefinder.server.entity.Place;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Place getPlace(){
        return createMockPlace();
    }

    private Place createMockPlace(){
        Place place = new Place();
        place.setId(1);
        place.setOwnerGoogleId("sdfsdfs435sdfs");
        place.setTitle("First place");

        return place;
    }

}
