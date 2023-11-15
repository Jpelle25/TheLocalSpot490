package thelocalspot.application.views.list.host;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import thelocalspot.application.data.entity.CoordUser;
import thelocalspot.application.data.entity.Event;
import thelocalspot.application.data.entity.Host;
import thelocalspot.application.data.service.CoordUserService;
import thelocalspot.application.data.service.EventService;
import thelocalspot.application.data.service.HostService;
import thelocalspot.application.data.service.PlaceService;
import thelocalspot.application.views.list.coordinator.EventForm;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Event Notifications")
@Route(value = "host", layout = HostMainLayout.class)
@PermitAll
public class HostEventsView extends VerticalLayout {
    private final CoordUserService coordUserService;
    private final HostService hostService;
    private final EventService eventService;
    private final PlaceService placeService;
    Grid<Event> grid;
    HostEventForm form;
    public HostEventsView(CoordUserService coordUserService, HostService hostService, EventService eventService, PlaceService placeService) {
        this.coordUserService = coordUserService;
        this.hostService = hostService;
        this.eventService = eventService;
        this.placeService = placeService;
        addClassName("event-view");
        setSizeFull();
        configureGrid();
        configureForm();
        add(
                getContent()
        );
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        List<Host> hosts = hostService.getHostEmail(principal.getAttribute("email"));
        if(!hosts.isEmpty()) {
            //List<Event> events = eventService.findAllEventsById(coordUsers.get(0));
            List<Event> events = eventService.hostEvents(hosts.get(0).getId());
            grid.setItems(events);
        }
    }

    private void configureForm() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        form = new HostEventForm(coordUserService, hostService.findAllHosts(), placeService.findAllPlaces(), eventService, placeService, principal);
        form.setWidth("25em");
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid = new Grid<>(Event.class);
        grid.addClassName("event-grid");
        grid.setSizeFull();
        grid.setColumns("eventName", "eventGenres", "eventTime", "dateStart", "dateEnd", "eventCapacity", "eventInfo", "maxTickets", "availableTickets", "ticketPrice");
        grid.addColumn(event -> event.getCoordUser().getCoordName()).setHeader("Coordinator Name");
        grid.addColumn(event -> event.getPlace().getPlaceName()).setHeader("Place Name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(gridEventComponentValueChangeEvent -> {
            enablingEvent(gridEventComponentValueChangeEvent.getValue());
        });
    }

    private void enablingEvent(Event event) {
        if(event == null) {
            closeEditor();
        } else {
            form.eventName.setValue(event.getEventName());
            form.eventGenres.setValue(event.getEventGenres());
            form.place.setValue(event.getPlace().getPlaceName());
            form.eventTime.setValue(event.getEventTime());
            form.dateStart.setValue(event.getDateStart());
            form.dateEnd.setValue(event.getDateEnd());
            form.eventCapacity.setValue(event.getEventCapacity());
            form.eventInfo.setValue(event.getEventInfo());
            form.maxTickets.setValue(event.getMaxTickets());
            form.ticketPrice.setValue(event.getTicketPrice().toString());
            form.setVisible(true);
            form.accept.addClickListener(buttonClickEvent -> {
                if(form.feedback.isEmpty()){
                    Notification nonCompleteRegistration = Notification.show("Please provide feedback", 3000, Notification.Position.BOTTOM_CENTER);
                    nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }else {
                    event.setEventStatus(true);
                    event.setPendingStatus(false);
                    event.setFeedback(form.feedback.getValue());
                    eventService.saveEvent(event);
                    closeEditor();
                    removeAll();
                    setSizeFull();
                    configureGrid();
                    configureForm();
                    add(getContent());
                    updateList();
                    closeEditor();
                    Notification accept = Notification.show("Event accepted", 3000, Notification.Position.BOTTOM_CENTER);
                    accept.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                }
            });
            form.deny.addClickListener(buttonClickEvent -> {
                if(form.feedback.isEmpty()){
                    Notification nonCompleteRegistration = Notification.show("Please provide feedback", 3000, Notification.Position.BOTTOM_CENTER);
                    nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }else {
                    closeEditor();
                    event.setPendingStatus(false);
                    event.setFeedback(form.feedback.getValue());
                    eventService.saveEvent(event);
                    removeAll();
                    setSizeFull();
                    configureGrid();
                    configureForm();
                    add(getContent());
                    updateList();
                    closeEditor();
                    Notification deny = Notification.show("Event denied", 3000, Notification.Position.BOTTOM_CENTER);
                    deny.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                }
            });
            form.cancel.addClickListener(buttonClickEvent -> {
                closeEditor();
                removeAll();
                setSizeFull();
                configureGrid();
                configureForm();
                add(getContent());
                updateList();
                closeEditor();
            });
        }
    }

    private void editEvent(Event event) {
        if(event == null){
            closeEditor();
        } else{
            form.setVisible(true);
            addClassName("editing");
        }
    }
}