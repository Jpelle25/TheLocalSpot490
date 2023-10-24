package thelocalspot.application.data.service;

import org.springframework.stereotype.Service;
import thelocalspot.application.data.entity.Event;
import thelocalspot.application.data.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void saveEvent(Event event) {
        if (event == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        eventRepository.save(event);
    }

    public List<Event> getEventDetails(){

        return eventRepository.findAll();
    }
}
