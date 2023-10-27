package thelocalspot.application.views.list.coordinator;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import thelocalspot.application.data.entity.*;
import thelocalspot.application.data.service.CoordUserService;
import thelocalspot.application.data.service.EventService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

public class EventForm extends FormLayout {

    EventService eventService;
    CoordUserService coordUserService;
    Binder<Event> binder = new BeanValidationBinder<>(Event.class);
    TextField coordId;
    TextField eventName = new TextField("Event Name");
    TimePicker eventTime = new TimePicker();
    DatePicker dateStart = new DatePicker();
    DatePicker dateEnd = new DatePicker();
    TextField eventCapacity = new TextField("Event Capacity");
    TextField eventInfo = new TextField("Event Information");
    TextField maxTickets = new TextField("Number of Tickets");
    TextField ticketPrice = new TextField("Ticket Price");
    ComboBox<Host> host = new ComboBox<>("Host");
    ComboBox<Place> place = new ComboBox<>("Place");
    Button finalize = new Button("Finalize");
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    private Event event;
    //TODO HostService hosts PlaceService places CoordService coords
    public EventForm(List<Host> hosts, List<Place> places, EventService eventService, CoordUserService coordUserService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        this.eventService = eventService;
        this.coordUserService = coordUserService;
        addClassName("event-form");
        binder.bindInstanceFields(this);
        host.setItems(hosts);
        host.setItemLabelGenerator(Host::getHostName);
        place.setItems(places);
        place.setItemLabelGenerator(Place::getPlaceName);
        eventTime.setLabel("Start Time");
        Locale finnishLocale = new Locale("fi", "FI");
        dateStart.setLabel("Start Date");
        dateStart.setLocale(finnishLocale);
        dateStart.setValue(LocalDate.now(ZoneId.systemDefault()));
        dateEnd.setLabel("End Date");
        dateEnd.setLocale(finnishLocale);
        dateEnd.setValue(LocalDate.now(ZoneId.systemDefault()));
        ticketPrice.setHelperText("Please enter in price with decimal point!");
        maxTickets.setMaxLength(6);
        List<CoordUser> coordUsers = coordUserService.getCoordUserEmail(principal.getAttribute("email"));
        coordId = new TextField("Coord Id", String.valueOf(coordUsers.get(0).getCoordId()), "");
        coordId.setReadOnly(true);
        add(
                eventName,
                coordId,
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
                eventService.saveEvent(new Event(eventName.getValue(), coordId.getValue(),  String.valueOf(host.getValue().getHostId()), String.valueOf(place.getValue().getPlaceId()), true, eventTime.getValue(), dateStart.getValue(), dateEnd.getValue(), eventCapacity.getValue(), eventInfo.getValue(), maxTickets.getValue(), Float.valueOf(ticketPrice.getValue())));
            }
        });
    }

    public void setEvent(Event event){

        this.event = event;
        binder.readBean(event);
    }
}