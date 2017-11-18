package com.bitirme.taksishare.mvc.controller;

/**
 * Created by exper on 27.04.2017.
 */

import com.bitirme.taksishare.mvc.models.ActionResult;
import com.bitirme.taksishare.mvc.models.TaxiJourney;
import com.bitirme.taksishare.services.TaxiJourneyService;
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
@RequestMapping(value = "/taxi-journey", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaxiJourneyController {

    @Autowired
    TaxiJourneyService taxiJourneyService;

    @RequestMapping(value = "/create-taxi-journey", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ActionResult createTaxiJourney(@ActiveUserId Integer userId,@RequestParam String fromWhere,  @RequestParam Double fromWhereE, @RequestParam Double fromWhereB,@RequestParam String destination,@RequestParam Double destinationE,@RequestParam Double destinationB, @RequestParam String date) {

        ActionResult actionResult = taxiJourneyService.createTaxiJourney(userId,fromWhere, fromWhereE , fromWhereB,destination, destinationE ,destinationB, date );
        return actionResult;

    }

    @RequestMapping(value = "/delete-taxi-journey", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ActionResult deleteTaxiJourney(@RequestParam  Integer taxiJourneyId) {

        return taxiJourneyService.deleteTaxiJourney(taxiJourneyId);

    }

    @RequestMapping(value = "/get-user-taxi-journeys", method = RequestMethod.GET, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public List<TaxiJourney> getTaxiJourneysByUserId(@ActiveUserId Integer userId) {

        return taxiJourneyService.getTaxiJourneysByUserId(userId);

    }

    @RequestMapping(value = "/update-taxi-journey", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ActionResult updateTaxiJourney(@ActiveUserId Integer taxiJourneyId ,@RequestParam String fromWhere, @RequestParam Double fromWhereE, @RequestParam Double fromWhereB,@RequestParam String destination,@RequestParam Double destinationE,@RequestParam Double destinationB, @RequestParam String date) {

        TaxiJourney taxiJourney = taxiJourneyService.getTaxiJourneyById(taxiJourneyId);
        taxiJourney.setFromWhere(fromWhere);
        taxiJourney.setFromWhereE(fromWhereE);
        taxiJourney.setFromWhereB(fromWhereB);
        taxiJourney.setDestination(destination);
        taxiJourney.setDestinationE(destinationE);
        taxiJourney.setDestinationB(destinationB);
        taxiJourney.setDate(date);

        return taxiJourneyService.updateTaxiJourney(taxiJourney);

    }

    @RequestMapping(value = "/search-results", method = RequestMethod.GET, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public List<TaxiJourney> getTaxiJourneysBySearch(@RequestParam String date,@RequestParam Double fromWhereE, @RequestParam Double fromWhereB,@RequestParam Double destinationE,@RequestParam Double destinationB) {

        return taxiJourneyService.getTaxiJourneysBySearch( date, fromWhereE,  fromWhereB , destinationE,  destinationB);

    }

    @RequestMapping(value = "/active-taxi-journeys", method = RequestMethod.GET, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public List<TaxiJourney> getActiveTaxiJourneysByUserId(@ActiveUserId Integer userId) {

        return taxiJourneyService.getActiveTaxiJourneysByUserId(userId);

    }
}
