package com.bitirme.taksishare.mvc.controller;

import com.bitirme.taksishare.mvc.models.ActionResult;
import com.bitirme.taksishare.mvc.models.TaxiJourney;
import com.bitirme.taksishare.services.TaxiJourney_UserService;
import com.bitirme.taksishare.spring.ActiveUserId;
import javafx.geometry.Pos;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/taxi-journey-user", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaxiJourney_UserController {

    @Autowired
    TaxiJourney_UserService taxiJourney_UserService;

    @RequestMapping(value = "/join-taxi-journey", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ActionResult joinTaxiJourney(@RequestParam Integer taxiJourneyId , @ActiveUserId Integer userId) {

        ActionResult actionResult = taxiJourney_UserService.joinTaxiJourney(taxiJourneyId, userId );
        return actionResult;

    }

    @RequestMapping(value = "/leave-taxi-journey", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ActionResult leaveTaxiJourney(@RequestParam Integer taxiJourneyId , @ActiveUserId Integer userId) {

        ActionResult actionResult = taxiJourney_UserService.leaveTaxiJourney(taxiJourneyId, userId );
        return actionResult;

    }
}
