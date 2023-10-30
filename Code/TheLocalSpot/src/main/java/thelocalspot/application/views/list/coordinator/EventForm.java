package thelocalspot.application.views.list.coordinator;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import thelocalspot.application.data.entity.*;
import thelocalspot.application.data.service.CoordUserService;
import thelocalspot.application.data.service.EventService;
import thelocalspot.application.data.service.PlaceService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EventForm extends FormLayout {
    TextField eventName = new TextField("Event Name");
    MultiSelectComboBox<String> eventGenres = new MultiSelectComboBox<>("Event Genres");
    TimePicker eventTime = new TimePicker("Start Time");
    DatePicker dateStart = new DatePicker("Start Date");
    DatePicker dateEnd = new DatePicker("End Date");
    TextField eventCapacity = new TextField("Event Capacity");
    TextField eventInfo = new TextField("Event Information");
    TextField maxTickets = new TextField("Number of Tickets");
    TextField ticketPrice = new TextField("Ticket Price");
    ComboBox<Host> host = new ComboBox<>("Host");
    ComboBox<Place> place = new ComboBox<>("Place");
    Button finalize = new Button("Finalize");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    EventService eventService;
    CoordUserService coordUserService;
    PlaceService placeService;
    public EventForm(CoordUserService coordUserService, List<Host> hosts, List<Place> places , EventService eventService, PlaceService placeService) {
        this.eventService = eventService;
        this.coordUserService = coordUserService;
        this.placeService = placeService;
        host.setItems(hosts);
        host.setItemLabelGenerator(Host::getHostName);
//        place.setReadOnly(true);
//        host.addValueChangeListener(comboBoxHostComponentValueChangeEvent -> {
//            place.setReadOnly(false);
//            Long hostId = host.getValue().getId();
//            List<Place> places1 = placeService.getHostId(hostId);
//            List<String> temp = new ArrayList<>();
//            for(int i = 0; i < places1.size(); i++) {
//                temp.add(places1.get(i).getPlaceName());
//            }
//            place.setItems(temp);
//            place.setItemLabelGenerator(places1.get(0).getPlaceName());
//        });
        place.setItems(places);
        place.setItemLabelGenerator(Place::getPlaceName);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        addClassName("event-form");
        Locale finnishLocale = new Locale("fi", "FI");
        dateStart.setLocale(finnishLocale);
        dateEnd.setLocale(finnishLocale);
        eventGenres.setItems("Music", "Comedy", "Theatre", "Gaming", "Sports", "Recreational");
        ticketPrice.setHelperText("Please enter in price with decimal point!");
        maxTickets.setMaxLength(6);
        add(
                eventName,
                eventGenres,
                host,
                place,
                eventTime,
                dateStart,
                dateEnd,
                eventCapacity,
                eventInfo,
                maxTickets,
                ticketPrice,
                finalize
        );
        finalize.addClickListener(buttonClickEvent ->{
            if(eventName.isEmpty() ||
                    eventTime.isEmpty() ||
                    eventGenres.isEmpty() ||
                    dateStart.isEmpty() ||
                    dateEnd.isEmpty() ||
                    eventCapacity.isEmpty() ||
                    eventInfo.isEmpty() ||
                    maxTickets.isEmpty() ||
                    ticketPrice.isEmpty() ||
                    host.isEmpty() ||
                    place.isEmpty()){
                Notification nonCompleteRegistration = Notification.show("Please enter in all the fields for registration", 3000, Notification.Position.BOTTOM_CENTER);
                nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
            else {
//                eventService.saveEvent(new Event("Event 1", "2000", host, 2, true, "Music", "12:00", "2023-12-30", "2023-12-31", "100", "This is the info for event A", "100", "100", 5.00));
                eventService.saveEvent(new Event(eventName.getValue(), "1000", host.getValue(), place.getValue(), true, eventGenres.getSelectedItems(), eventTime.getValue(), dateStart.getValue(), dateEnd.getValue(), eventCapacity.getValue(), eventInfo.getValue(), maxTickets.getValue(), Float.valueOf(ticketPrice.getValue())));
            }
        });
    }

//    public void setEvent(Event event){
//
//        this.event = event;
//    }
//    public static abstract class EventFormEvent extends ComponentEvent<EventForm> {
//        private Event event;
//
//        protected EventFormEvent(EventForm source, Event event) {
//            super(source, false);
//            this.event = event;
//        }
//
//        public Event getEvent() {
//            return event;
//        }
//    }
//
//    public static class SaveEvent extends EventFormEvent {
//        SaveEvent(EventForm source, Event event) {
//            super(source, event);
//        }
//    }
//
//    public static class DeleteEvent extends EventFormEvent {
//        DeleteEvent(EventForm source, Event event) {
//            super(source, event);
//        }
//
//    }
//
//    public static class CloseEvent extends EventFormEvent {
//        CloseEvent(EventForm source) {
//            super(source, null);
//        }
//    }
//
//    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
//        return addListener(DeleteEvent.class, listener);
//    }
//
//    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
//        return addListener(SaveEvent.class, listener);
//    }
//    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
//        return addListener(CloseEvent.class, listener);
//    }

}