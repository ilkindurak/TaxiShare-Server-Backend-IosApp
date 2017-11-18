package com.bitirme.taksishare.mvc.models;

/**
 * Created by exper on 03.06.2017.
 */
public class Notification {

    private Integer id;
    private Integer receiverId;
    private Integer senderId;
    private Integer taxiJourneyId;
    private String  message;
    private String  date;



    public Notification(){}
    public Notification(Integer id, Integer receiverId , Integer senderId, Integer taxiJourneyId, String  message,String date){
        this.id=id;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.taxiJourneyId = taxiJourneyId;
        this.message = message;
        this.date = date;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceiverId() {return receiverId;}

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getTaxiJourneyId() {
        return taxiJourneyId;
    }

    public void setTaxiJourneyId(Integer taxiJourneyId) {
        this.taxiJourneyId = taxiJourneyId;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }


}
