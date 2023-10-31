package thelocalspot.application.data.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import thelocalspot.application.data.entity.CoordUser;
import thelocalspot.application.data.entity.Event;
import java.util.List;
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select c from Event c " +
            "where lower(c.eventName) like lower(concat('%', :searchTerm, '%')) ")
    List<Event> search(@Param("searchTerm") String searchTerm);
    @Query("select c from Event c " +
            "where c.coordUser = :searchTerm")
    List<Event> coordID(@Param("searchTerm") CoordUser searchTerm);
    @Query("select c from Event c " +
            "where c.eventStatus = true")
    List<Event> eventStatus();
}