package com.placefinder.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/place")
public class PlaceController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getPlace(){
        return "place";
    }

}
