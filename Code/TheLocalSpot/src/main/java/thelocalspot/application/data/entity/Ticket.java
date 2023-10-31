package thelocalspot.application.data.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Ticket extends AbstractEntity{
    private long eventID;
    private String ticketOwnerID;
    private String purchaseDate;
    private boolean soldStatus;
    public Ticket() {
    }
    public Ticket(long eventID, String ticketOwnerID, String purchaseDate, boolean soldStatus) {
        this.eventID = eventID;
        this.ticketOwnerID = ticketOwnerID;
        this.purchaseDate = purchaseDate;
        this.soldStatus = soldStatus;
    }
    public long getEventID() {
        return eventID;
    }
    public void setEventID(long eventID) {
        this.eventID = eventID;
    }
    public String getTicketOwnerID() {
        return ticketOwnerID;
    }
    public void setTicketOwnerID(String ticketOwnerID) {
        this.ticketOwnerID = ticketOwnerID;
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