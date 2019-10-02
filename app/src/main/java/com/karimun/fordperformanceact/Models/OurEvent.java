package com.karimun.fordperformanceact.Models;

public class OurEvent {

    private String eventId, title, dateStart, timeStart, dateEnd, timeEnd, location, dayOfWeekStart, dayCounter;
    private boolean sendNotification;

    OurEvent() {}

    OurEvent(String eventId, String title, String dateStart, String timeStart
            , String dateEnd, String timeEnd, String location, String dayCounter, boolean sendNotification) {

        this.eventId = eventId;
        this.title = title;
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.dateEnd = dateEnd;
        this.timeEnd = timeEnd;
        this.location = location;
        this.dayCounter = dayCounter;
        this.sendNotification = sendNotification;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDayOfWeekStart() {
        return dayOfWeekStart;
    }

    public void setDayOfWeekStart(String dayOfWeekStart) {
        this.dayOfWeekStart = dayOfWeekStart;
    }

    public String getDayCounter() {
        return dayCounter;
    }

    public void setDayCounter(String dayCounter) {
        this.dayCounter = dayCounter;
    }

    public boolean isSendNotification() {
        return sendNotification;
    }

    public void setSendNotification(boolean sendNotification) {
        this.sendNotification = sendNotification;
    }
}
