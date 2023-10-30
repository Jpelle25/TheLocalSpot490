package thelocalspot.application.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
@Entity
@Table(name = "EVENTS")
public class Event extends AbstractEntity {
    private String coordUserId;
    //    private String hostUserId;
//    private String placeId;
    private boolean eventStatus;
    private String eventGenres;
    private String eventName;
    private LocalTime eventTime;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String eventCapacity;
    private String eventInfo;
    private String maxTickets;
    private String availableTickets;
    private Float ticketPrice;

    @ManyToOne
    @JoinColumn(name = "host_id")
    @NotNull
    @JsonIgnoreProperties({"employees"})
    private Host host;

    @NotNull
    @ManyToOne
    private Place place;

    public Event() {
    }

    public Event(String eventName, String coordUserId, Host host, Place place, boolean eventStatus, Set<String> eventGenres, LocalTime eventTime, LocalDate dateStart, LocalDate dateEnd, String eventCapacity, String eventInfo, String maxTickets, Float ticketPrice) {
        this.coordUserId = coordUserId;
        this.host = host;
        this.place = place;
        this.eventStatus = eventStatus;
        this.eventGenres = eventGenresToString(eventGenres);
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.eventCapacity = eventCapacity;
        this.eventInfo = eventInfo;
        this.maxTickets = maxTickets;
        this.availableTickets = maxTickets;
        this.ticketPrice = ticketPrice;
    }

    private String eventGenresToString(Set<String> eventGenres){
        return String.join(",",eventGenres);
    }

    public Host getHost() {
        return host;
    }

    public Place getPlace() {
        return place;
    }

//    public String getHostUserId() {
//        return hostUserId;
//    }
//
//    public void setHostUserId(String hostUserId) {
//        this.hostUserId = hostUserId;
//    }
//
//    public String getPlaceId() {
//        return placeId;
//    }
//
//    public void setPlaceId(String placeId) {
//        this.placeId = placeId;
//    }

    public String getEventGenres() {
        return eventGenres;
    }

    public void setEventGenres(String eventGenres) {
        this.eventGenres = eventGenres;
    }

    public String getCoordUserId() {
        return coordUserId;
    }

    public void setCoordUserId(String coordUserId) {
        this.coordUserId = coordUserId;
    }

    public boolean isEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(boolean eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getEventCapacity() {
        return eventCapacity;
    }

    public void setEventCapacity(String eventCapacity) {
        this.eventCapacity = eventCapacity;
    }

    public String getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    public String getMaxTickets() {
        return maxTickets;
    }

    public void setMaxTickets(String maxTickets) {
        this.maxTickets = maxTickets;
    }

    public String getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(String availableTickets) {
        this.availableTickets = availableTickets;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
