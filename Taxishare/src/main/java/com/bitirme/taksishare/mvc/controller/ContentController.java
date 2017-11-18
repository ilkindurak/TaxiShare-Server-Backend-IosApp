package com.bitirme.taksishare.mvc.controller;

import com.bitirme.taksishare.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by YunusS on 4/21/2016.
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    ContentService contentService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> test() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("test", "OK");
        return result;
    }
}
