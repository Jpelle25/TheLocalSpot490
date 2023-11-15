package thelocalspot.application.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import thelocalspot.application.data.entity.Event;
import thelocalspot.application.data.entity.Host;

import java.util.List;

public interface HostRepository extends JpaRepository<Host, Long> {
    @Query("select c from Host c " +
            "where c.email = :searchTerm")
    List<Host> hostSelf(@Param("searchTerm") String searchTerm);
}