package thelocalspot.application.data.service;

import org.springframework.stereotype.Service;
import thelocalspot.application.data.entity.CoordUser;
import thelocalspot.application.data.entity.Event;
import thelocalspot.application.data.repository.EventRepository;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAllEventsByIdAndPendingTrue(CoordUser filterText) {
        return eventRepository.coordID(filterText);
    }

    public List<Event> findAllEventsByIdAndApprove(CoordUser filterText) {
        return eventRepository.coordApprove(filterText);
    }

    public List<Event> findAllEventsByIdAndDeny(CoordUser filterText) {
        return eventRepository.coordDeny(filterText);
    }

    public List<Event> findAllEventsStatusTrue() {
        return eventRepository.eventStatus();
    }

    public List<Event> hostEvents(Long searchTerm) {
        return eventRepository.hostEvents(searchTerm);
    }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    public void saveEvent(Event event) {
        if (event == null) {
            System.err.println("Event is null. ");
            return;
        }
        eventRepository.save(event);
    }
}