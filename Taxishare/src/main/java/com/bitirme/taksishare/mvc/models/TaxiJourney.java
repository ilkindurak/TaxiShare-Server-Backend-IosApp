package com.bitirme.taksishare.mvc.models;

/**
 * Created by exper on 23.04.2017.
 */
public class TaxiJourney {

    private Integer id;
    private String fromWhere;
    private Double fromWhereE;
    private Double fromWhereB;
    private String destination;
    private Double destinationE;
    private Double destinationB;
    private String date;


    public TaxiJourney(){}
    public TaxiJourney(Integer id, String fromWhere , Double fromWhereE, Double fromWhereB, String destination, Double destinationE, Double destinationB,    String date){
        this.id=id;
        this.fromWhere = fromWhere;
        this.fromWhereE = fromWhereE;
        this.fromWhereB = fromWhereB;
        this.destination = destination;
        this.destinationE = destinationE;
        this.destinationB = destinationB;
        this.date = date;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromWhere(){return fromWhere;}

    public void setFromWhere(String fromWhere){this.fromWhere = fromWhere;}


    public Double getFromWhereE() { return fromWhereE; }

    public void setFromWhereE(Double fromWhereE) { this.fromWhereE = fromWhereE; }

    public String getDestination(){return destination;}

    public void setDestination(String destination){this.destination = destination;}

    public Double getDestinationE() { return destinationE; }

    public void setDestinationE(Double destinationE) { this.destinationE = destinationE; }

    public Double getFromWhereB() { return fromWhereB; }

    public void setFromWhereB(Double fromWhereB) { this.fromWhereB = fromWhereB; }

    public Double getDestinationB() { return destinationB; }

    public void setDestinationB(Double destinationB) { this.destinationB = destinationB; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

}
