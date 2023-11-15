package thelocalspot.application.data.service;
import org.springframework.stereotype.Service;
import thelocalspot.application.data.entity.GenUser;
import thelocalspot.application.data.entity.Ticket;
import thelocalspot.application.data.repository.GenUserRepository;
import thelocalspot.application.data.repository.TicketRepository;
import java.util.List;
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    public TicketService (TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }
    public void saveTicket(Ticket ticket) {
        if (ticket == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        ticketRepository.save(ticket);
    }
}