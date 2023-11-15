package thelocalspot.application.views.list.coordinator;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import thelocalspot.application.data.entity.*;
import thelocalspot.application.data.service.CoordUserService;
import thelocalspot.application.data.service.EventService;
import thelocalspot.application.data.service.PlaceService;
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class EventForm extends FormLayout {
    TextField eventName = new TextField("Event Name");
    ComboBox<String> eventGenres = new ComboBox<>("Event Genres");
    TimePicker eventTime = new TimePicker("Start Time");
    DatePicker dateStart = new DatePicker("Start Date");
    DatePicker dateEnd = new DatePicker("End Date");
    TextField placeCapacity = new TextField("Place Capacity");
    TextField eventCapacity = new TextField("Event Capacity");
    TextField eventInfo = new TextField("Event Information");
    TextField maxTickets = new TextField("Number of Tickets");
    TextField ticketPrice = new TextField("Ticket Price");
    ComboBox<Host> host = new ComboBox<>("Host");
    ComboBox<Place> place = new ComboBox<>("Place");
    Button createEvent = new Button("Create Event");
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete");
    Button edit = new Button("Edit");
    EventService eventService;
    CoordUserService coordUserService;
    PlaceService placeService;
    public EventForm(CoordUserService coordUserService, List<Host> hosts, List<Place> places , EventService eventService, PlaceService placeService, OAuth2AuthenticatedPrincipal principal) {
        this.eventService = eventService;
        this.coordUserService = coordUserService;
        this.placeService = placeService;
        List<CoordUser> coordUserSelf = coordUserService.getCoordUserEmail(principal.getAttribute("email"));
        String[] selfGenre = coordUserSelf.get(0).getCoordGenre().split(",");
        eventGenres.setItems(selfGenre);
        host.setItems(hosts);
        host.setItemLabelGenerator(Host::getHostName);
        place.setReadOnly(true);
        placeCapacity.setReadOnly(true);
        host.addValueChangeListener(comboBoxHostComponentValueChangeEvent -> {
            place.setReadOnly(false);
            List<Place> placeTest = placeService.getHostId(host.getValue().getId());
            place.setItems(placeTest);
            place.setItemLabelGenerator(Place::getPlaceName);
        });
        addClassName("event-form");
        Locale finnishLocale = new Locale("fi", "FI");
        dateStart.setLocale(finnishLocale);
        dateEnd.setLocale(finnishLocale);
        maxTickets.setMaxLength(6);
        dateStart.addValueChangeListener(e -> dateEnd.setMin(e.getValue()));
        dateEnd.addValueChangeListener(e -> dateStart.setMax(e.getValue()));
        place.addValueChangeListener(comboBoxPlaceComponentValueChangeEvent -> {
            placeCapacity.setValue(place.getValue().getPlaceCapacity());
        });
        createEvent.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        add(
                eventName,
                eventGenres,
                host,
                place,
                eventTime,
                dateStart,
                dateEnd,
                placeCapacity,
                eventCapacity,
                eventInfo,
                maxTickets,
                ticketPrice,
                createEvent,
                cancel
        );
    }
}