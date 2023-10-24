package thelocalspot.application.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thelocalspot.application.data.entity.Event;

import java.util.List;
public interface EventRepository extends JpaRepository<Event, Long> {
}
