package com.example.Ecosphere.events;

public class Event {

    private long eventID;
    private String eventName;
    private String eventDescription;
    private String eventLink;
    private String eventImg;

    Event(){
        eventID = -1;
        eventName = "";
        eventDescription = "";
        eventImg = "";
        eventLink = "";
    }

    public long getEventID() {
        return eventID;
    }

    public void setEventID(long eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventLink() {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }

    public boolean createEvent(EventPersistence eventPersistence){
        return eventPersistence.registerEvent(this);
    }
}
