package thelocalspot.application.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import thelocalspot.application.data.entity.CoordUser;
import thelocalspot.application.data.entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select c from Event c " +
            "where c.coordUser = :searchTerm and c.pendingStatus = true")
    List<Event> coordID(@Param("searchTerm") CoordUser searchTerm);

    @Query("select c from Event c " +
            "where c.coordUser = :searchTerm and c.eventStatus = true")
    List<Event> coordApprove(@Param("searchTerm") CoordUser searchTerm);

    @Query("select c from Event c " +
            "where c.coordUser = :searchTerm and c.pendingStatus = false and c.eventStatus = false")
    List<Event> coordDeny(@Param("searchTerm") CoordUser searchTerm);

    @Query("select c from Event c " +
            "where c.eventStatus = true")
    List<Event> eventStatus();

    @Query("select c from Event c " +
            "where c.host.id = :searchTerm and c.pendingStatus = true")
    List<Event> hostEvents(@Param("searchTerm") Long searchTerm);
}