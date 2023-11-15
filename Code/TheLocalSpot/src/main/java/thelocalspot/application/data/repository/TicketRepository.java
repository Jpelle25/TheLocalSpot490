package thelocalspot.application.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import thelocalspot.application.data.entity.CoordUser;
import thelocalspot.application.data.entity.Event;
import thelocalspot.application.data.entity.GenUser;
import thelocalspot.application.data.entity.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("select c from Ticket c " +
            "where c.genUser = :genUser")
    List<Ticket> ticketOwnership(@Param("genUser") GenUser genUser);

    @Query("select c from Ticket c " +
            "where c.genUser = :searchTerm and c.event.dateStart >= :today")
    List<Ticket> transferableTickets(@Param("searchTerm") GenUser searchTerm,@Param("today") LocalDate today);
}
