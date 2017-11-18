package com.bitirme.taksishare.mvc.models;

/**
 * Created by exper on 05.05.2017.
 */
public class TaxiJourney_User {

    private Integer taxiJourneyId;
    private Integer userId;

    public TaxiJourney_User(){};


    public TaxiJourney_User(Integer taxiJourneyId, Integer userId) {
        this.taxiJourneyId = taxiJourneyId;
        this.userId = userId;
    }

    public Integer getTaxiJourneyId() {
        return taxiJourneyId;
    }

    public void setTaxiJourneyId(Integer taxiJourneyId) {
        this.taxiJourneyId = taxiJourneyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
