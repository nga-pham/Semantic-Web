package semanticweb.uibk.at.eventfulwrapper.model;

import java.util.Date;

/**
 * Created by ngapham on 28.01.18.
 */

public class Event extends Thing {
    Date startDate;
    Date endDate;
    EventVenue venue;
    boolean isAllDay;

    public Event(String id, String title, String description, String url) {
        this.identifier = id;
        this.name = title;
        this.description = description;
        this.url = url;
    }

    public Event() {
        isAllDay = false;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public EventVenue getVenue() {
        return venue;
    }

    public void setVenue(EventVenue venue) {
        this.venue = venue;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    @Override
    public String toString() {
        return "Event{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
//                ", isAllDay=" + isAllDay +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
