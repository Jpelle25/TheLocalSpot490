package thelocalspot.application.data.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import thelocalspot.application.data.entity.Ticket;
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}