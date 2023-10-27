package thelocalspot.application.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String coordUserId;
    private String hostUserId;
    private String placeId;

    private boolean eventStatus;
    private String eventName;
    private LocalTime eventTime;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String eventCapacity;
    private String eventInfo;
    private String maxTickets;
    private String availableTickets;
    private Float ticketPrice;

    public Event() {
    }

    public Event(String eventName, String coordUserId, String hostUserId, String placeId, boolean eventStatus, LocalTime eventTime, LocalDate dateStart, LocalDate dateEnd, String eventCapacity, String eventInfo, String maxTickets, Float ticketPrice) {
        this.coordUserId = coordUserId;
        this.hostUserId = hostUserId;
        this.placeId = placeId;
        this.eventStatus = eventStatus;
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
}
