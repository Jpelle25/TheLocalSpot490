package thelocalspot.application.views.list.host;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import thelocalspot.application.data.entity.Host;
import thelocalspot.application.data.entity.Place;
import thelocalspot.application.data.service.CoordUserService;
import thelocalspot.application.data.service.EventService;
import thelocalspot.application.data.service.PlaceService;

import java.util.List;
import java.util.Locale;

public class HostEventForm extends FormLayout {
    TextField eventName = new TextField("Event Name");
    TextField eventGenres = new TextField("Event Genres");
    TimePicker eventTime = new TimePicker("Start Time");
    DatePicker dateStart = new DatePicker("Start Date");
    DatePicker dateEnd = new DatePicker("End Date");
    TextField eventCapacity = new TextField("Event Capacity");
    TextField eventInfo = new TextField("Event Information");
    TextField maxTickets = new TextField("Number of Tickets");
    TextField ticketPrice = new TextField("Ticket Price");
    TextField place = new TextField("Place");
    TextArea feedback = new TextArea("Feedback");
    Button accept = new Button("Accept");
    Button deny = new Button("Deny");
    Button cancel = new Button("Cancel");
    EventService eventService;
    CoordUserService coordUserService;
    PlaceService placeService;
    public HostEventForm(CoordUserService coordUserService, List<Host> hosts, List<Place> places, EventService eventService, PlaceService placeService, OAuth2AuthenticatedPrincipal principal) {
        this.eventService = eventService;
        this.coordUserService = coordUserService;
        this.placeService = placeService;
        eventName.setReadOnly(true);
        eventGenres.setReadOnly(true);
        place.setReadOnly(true);
        eventTime.setReadOnly(true);
        dateStart.setReadOnly(true);
        dateEnd.setReadOnly(true);
        eventCapacity.setReadOnly(true);
        eventInfo.setReadOnly(true);
        maxTickets.setReadOnly(true);
        ticketPrice.setReadOnly(true);
        addClassName("event-form");
        Locale finnishLocale = new Locale("fi", "FI");
        dateStart.setLocale(finnishLocale);
        dateEnd.setLocale(finnishLocale);
        dateStart.addValueChangeListener(e -> dateEnd.setMin(e.getValue()));
        dateEnd.addValueChangeListener(e -> dateStart.setMax(e.getValue()));
        maxTickets.setMaxLength(6);
        accept.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deny.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        add(
                eventName,
                eventGenres,
                place,
                eventTime,
                dateStart,
                dateEnd,
                eventCapacity,
                eventInfo,
                maxTickets,
                ticketPrice,
                feedback,
                accept,
                deny,
                cancel
        );
    }
}