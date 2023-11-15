package thelocalspot.application.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TICKETS")
public class Ticket extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "event_id")
    @NotNull
    @JsonIgnoreProperties({"employees"})
    private Event event;

    @ManyToOne
    @JoinColumn(name = "gen_user_id")
    @NotNull
    @JsonIgnoreProperties({"employees"})
    private GenUser genUser;

    private String purchaseDate;

    private boolean soldStatus;

    public Ticket() {
    }

    public Ticket(Event event, GenUser genUser, String purchaseDate, boolean soldStatus) {
        this.event = event;
        this.genUser = genUser;
        this.purchaseDate = purchaseDate;
        this.soldStatus = soldStatus;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public GenUser getGenUser() {
        return genUser;
    }

    public void setGenUser(GenUser genUser) {
        this.genUser = genUser;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isSoldStatus() {
        return soldStatus;
    }

    public void setSoldStatus(boolean soldStatus) {
        this.soldStatus = soldStatus;
    }
}
