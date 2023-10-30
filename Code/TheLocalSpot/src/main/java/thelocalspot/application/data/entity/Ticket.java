package thelocalspot.application.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long eventID;

    private long ticketOwnerID;

    private String purchaseDate;

    private boolean soldStatus;

    public Ticket() {
    }

    public Ticket(long id, long eventID, long ticketOwnerID, String purchaseDate, boolean soldStatus) {
        this.id = id;
        this.eventID = eventID;
        this.ticketOwnerID = ticketOwnerID;
        this.purchaseDate = purchaseDate;
        this.soldStatus = soldStatus;
    }
}
