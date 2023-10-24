package thelocalspot.application.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long coordUserId;
    private Long hostUserId;
    private Long placeId;

    private boolean eventSatus;
    private String eventName;
    private String eventTime;
    private String dateStart;
    private String dateEnd;
    private String eventCapacity;
    private String eventInfo;
    private String maxTickets;
    private String availableTickets;
    private Float ticketPrice;

    public Event() {
    }

    public Event(String eventName, Long coordUserId, Long hostUserId, Long placeId, boolean eventSatus, String eventTime, String dateStart, String dateEnd, String eventCapacity, String eventInfo, String maxTickets, String availableTickets, Float ticketPrice) {
        this.coordUserId = coordUserId;
        this.hostUserId = hostUserId;
        this.placeId = placeId;
        this.eventSatus = eventSatus;
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.eventCapacity = eventCapacity;
        this.eventInfo = eventInfo;
        this.maxTickets = maxTickets;
        this.availableTickets = availableTickets;
        this.ticketPrice = ticketPrice;
    }
}
