package thelocalspot.application.data.service;
import com.vaadin.collaborationengine.Backend;
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
    public List<Event> getEventDetails(){
        return eventRepository.findAll();
    }
    public List<Event> findAllEvents(String filterText){
        if(filterText == null || filterText.isEmpty()){
            return eventRepository.findAll();
        }
        else {
            return eventRepository.search(filterText);
        }
    }
    public List<Event> findAllEventsById(CoordUser filterText){
        return eventRepository.coordID(filterText);
    }
    public List<Event> findAllEventsStatusTrue(){
        return eventRepository.eventStatus();
    }
    public void deleteEvent(Event event){
        eventRepository.delete(event);
    }
    public void saveEvent(Event event){
        if(event == null){
            System.err.println("Event is null. ");
            return;
        }
        eventRepository.save(event);
    }

}