package com.bitirme.taksishare.services;

/**
 * Created by exper on 24.04.2017.
 */

import com.bitirme.taksishare.db.TaxiJourneyDao;
import com.bitirme.taksishare.db.UsersDao;
import com.bitirme.taksishare.mvc.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.directory.SearchResult;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TaxiJourneyService {

    @Autowired
    TaxiJourneyDao taxiJourneyDao;

    @Autowired
    UsersDao usersDao;


    public ActionResult createTaxiJourney (Integer userId , String fromWhere, Double fromWhereE, Double fromWhereB,String destination,Double destinationE, Double destinationB , String date) {

        TaxiJourney taxiJourney = new TaxiJourney();
        taxiJourney.setFromWhere(fromWhere);
        taxiJourney.setFromWhereE(fromWhereE);
        taxiJourney.setFromWhereB(fromWhereB);
        taxiJourney.setDestination(destination);
        taxiJourney.setDestinationE(destinationE);
        taxiJourney.setDestinationB(destinationB);
        taxiJourney.setDate(date);

        Integer newTaxiJourneyId = taxiJourneyDao.createTaxiJourney(userId , taxiJourney);

        if (newTaxiJourneyId > 0) {
            taxiJourney.setId(newTaxiJourneyId);
            return new ActionResult(true, "Taksi yolculuğu yaratıldı.");
        }else {
            return new ActionResult(false, "Taksi yolculuğu yaratılamadı .");
        }

    }



    public ActionResult deleteTaxiJourney (Integer taxiJourneyId) {

        if (taxiJourneyDao.deleteTaxiJourney(taxiJourneyId) > 0) {

            return new ActionResult(true, "Taksi yolculuğu başarıyla silindi.");

        }else {

            return new ActionResult(false, "Taksi yolculuğu silinemedi .");

        }

    }


    public ActionResult updateTaxiJourney(TaxiJourney taxiJourney) {

        if (taxiJourneyDao.updateTaxiJourney(taxiJourney) > 0) {

            return new ActionResult(true, "Taksi yolculuğunuzun bilgileri başarıyla güncellendi.");

        }else {

            return new ActionResult(false, "Taksi yolculuğunuzun bilgilerini güncellerken bir sorun oluştu. Lütfen tekrar deneyiniz.");

        }

    }


    public List<TaxiJourney> getTaxiJourneysByUserId (Integer userId) {

        return taxiJourneyDao.getTaxiJourneysByUserId(userId);

    }

    public TaxiJourney getTaxiJourneyById(Integer taxiJourneyId) {

        return taxiJourneyDao.getTaxiJourneyById(taxiJourneyId);
    }

    public List<TaxiJourney> getTaxiJourneysBySearch (String date,Double fromWhereE, Double fromWhereB ,Double destinationE, Double destinationB) {

         List<TaxiJourney> searchResults= new ArrayList<TaxiJourney>() ;
         List<TaxiJourney> taxiJourneys = taxiJourneyDao.getTaxiJourneysByDate(date);
        for(TaxiJourney Journey : taxiJourneys) {
           double distanceFromWhere = distance(Journey.getFromWhereE(),Journey.getFromWhereB() ,fromWhereE , fromWhereB, 'K');
            double distanceDestination = distance(Journey.getDestinationE(),Journey.getDestinationB() ,destinationE , destinationB, 'K');

            distanceFromWhere *= 1000;
            distanceDestination *=1000;

           if(distanceFromWhere<=250&&distanceDestination<=250){
               searchResults.add(Journey);
           }
        }
            return searchResults;
        }


    public User getUserInTaxiJourney(Integer taxiJourneyId) {

        return usersDao.getUserById(taxiJourneyDao.getUserIdInTaxiJourney(taxiJourneyId));
    }

    public List<TaxiJourney> getActiveTaxiJourneysByUserId(Integer userId ) {

        String date = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        return taxiJourneyDao.getActiveTaxiJourneysByUserId(userId, date);

    }

    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }





}
