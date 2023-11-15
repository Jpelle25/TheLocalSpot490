package thelocalspot.application.views.list.coordinator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
import java.util.ArrayList;
import java.util.List;
@PageTitle("Coordinator")
@Route(value = "coordinator-pending-events", layout = CoordinatorMainLayout.class)
@PermitAll
public class PendingEventsView extends VerticalLayout {
    private final CoordUserService coordUserService;
    private final HostService hostService;
    private final EventService eventService;
    private final PlaceService placeService;
    Grid<Event> grid;
    EventForm form;
    public PendingEventsView(CoordUserService coordUserService, HostService hostService, EventService eventService, PlaceService placeService) {
        this.coordUserService = coordUserService;
        this.hostService = hostService;
        this.eventService = eventService;
        this.placeService = placeService;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        addClassName("event-view");
        setSizeFull();
        configureGrid();
        configureForm(principal);
        add(
                getToolbar(principal),
                getContent()
        );
        updateList(principal);
        closeEditor();
    }
    private void closeEditor() {
        form.setVisible(false);
        removeClassName("editing");
    }
    private void updateList(OAuth2AuthenticatedPrincipal principal) {
        List<CoordUser> coordUsers = coordUserService.getCoordUserEmail(principal.getAttribute("email"));
        if(!coordUsers.isEmpty()) {

            List<Event> events = eventService.findAllEventsByIdAndPendingTrue(coordUsers.get(0));
            grid.setItems(events);
        }
    }
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }
    private void configureForm(OAuth2AuthenticatedPrincipal principal) {
// List<CoordUser> coordUsers = coordUserService.getCoordUserEmail(principal.getAttribute("email"));
        form = new EventForm(coordUserService, hostService.findAllHosts(), placeService.findAllPlaces(), eventService, placeService, principal);
        form.setWidth("25em");
        form.createEvent.addClickListener(buttonClickEvent -> {
            if(form.eventName.isEmpty()||
                    form.eventTime.isEmpty() ||
                    form.eventGenres.isEmpty() ||
                    form.dateStart.isEmpty() ||
                    form.dateEnd.isEmpty() ||
                    form.eventCapacity.isEmpty() ||
                    form.eventInfo.isEmpty() ||
                    form.maxTickets.isEmpty() ||
                    form.ticketPrice.isEmpty() ||
                    form.host.isEmpty() ||
                    form.place.isEmpty()){
                Notification nonCompleteRegistration = Notification.show("Please enter in all the fields for create event", 3000, Notification.Position.BOTTOM_CENTER);
                nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
            else if (Integer.valueOf(form.eventCapacity.getValue()) > Integer.valueOf(form.placeCapacity.getValue())){
                Notification nonCompleteRegistration = Notification.show("Event Capacity cannot be greater than Place Capacity", 3000, Notification.Position.BOTTOM_CENTER);
                nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
            else if (Integer.valueOf(form.maxTickets.getValue()) > Integer.valueOf(form.eventCapacity.getValue())){
                Notification nonCompleteRegistration = Notification.show("Max Tickets cannot be greater than Event Capacity", 3000, Notification.Position.BOTTOM_CENTER);
                nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
            else {
                List<CoordUser> coordUserSession = coordUserService.getCoordUserEmail(principal.getAttribute("email"));
                Long coordUserSessionID = coordUserSession.get(0).getId();
                eventService.saveEvent(new Event(form.eventName.getValue(), coordUserSession.get(0), form.host.getValue(), form.place.getValue(),
                        false, true, form.eventGenres.getValue(), form.eventTime.getValue(), form.dateStart.getValue(),
                        form.dateEnd.getValue(), form.eventCapacity.getValue(), form.eventInfo.getValue(), form.maxTickets.getValue(), Float.valueOf(form.ticketPrice.getValue()), ""));
                closeEditor();
                removeAll();
                setSizeFull();
                configureGrid();
                configureForm(principal);
                add(
                        getToolbar(principal),
                        getContent()
                );
                updateList(principal);
                closeEditor();
            }
        });
        form.cancel.addClickListener(buttonClickEvent -> {
            closeEditor();
        });
    }

    private Component getToolbar(OAuth2AuthenticatedPrincipal principal) {
        Button addEventButton = new Button("Add Event");
        addEventButton.addClickListener(e -> addEvent());
        HorizontalLayout toolbar = new HorizontalLayout(addEventButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    private void addEvent() {
        grid.asSingleSelect().clear();
        createEvent(new Event());
    }
    private void configureGrid() {
        grid = new Grid<>(Event.class);
        grid.addClassName("event-grid");
        grid.setSizeFull();
        grid.setColumns("eventName", "eventGenres", "eventTime", "dateStart", "dateEnd", "eventCapacity", "eventInfo", "maxTickets", "availableTickets", "ticketPrice");
        grid.addColumn(event -> event.getHost().getHostName()).setHeader("Host");
        grid.addColumn(event -> event.getPlace().getPlaceName()).setHeader("Place");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(gridEventComponentValueChangeEvent -> {
            editEvent(gridEventComponentValueChangeEvent.getValue());
        });
    }
    private void editEvent(Event event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        if(event == null) {
            closeEditor();
        } else {
            form.eventName.setValue(event.getEventName());
            form.host.setItems(hostService.findAllHosts());
            form.place.setItems(placeService.findAllPlaces());
            form.eventTime.setValue(event.getEventTime());
            form.dateStart.setValue(event.getDateStart());
            form.dateEnd.setValue(event.getDateEnd());
            form.eventCapacity.setValue(event.getEventCapacity());
            form.eventInfo.setValue(event.getEventInfo());
            form.maxTickets.setValue(event.getMaxTickets());
            form.ticketPrice.setValue(event.getTicketPrice().toString());
            form.remove(form.createEvent);
            form.remove(form.cancel);
            form.add(form.edit);
            form.edit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            form.add(form.delete);
            form.delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
            form.add(form.cancel);
            form.cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            form.edit.addClickListener(buttonClickEvent -> {
                if(form.eventName.isEmpty()||
                        form.eventTime.isEmpty() ||
                        form.eventGenres.isEmpty() ||
                        form.dateStart.isEmpty() ||
                        form.dateEnd.isEmpty() ||
                        form.eventCapacity.isEmpty() ||
                        form.eventInfo.isEmpty() ||
                        form.maxTickets.isEmpty() ||
                        form.ticketPrice.isEmpty() ||
                        form.host.isEmpty() ||
                        form.place.isEmpty()){
                    Notification nonCompleteRegistration = Notification.show("Please enter in all the fields for create event", 3000, Notification.Position.BOTTOM_CENTER);
                    nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
                else if (Integer.valueOf(form.eventCapacity.getValue()) > Integer.valueOf(form.placeCapacity.getValue())){
                    Notification nonCompleteRegistration = Notification.show("Event Capacity cannot be greater than Place Capacity", 3000, Notification.Position.BOTTOM_CENTER);
                    nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
                else if (Integer.valueOf(form.maxTickets.getValue()) > Integer.valueOf(form.eventCapacity.getValue())){
                    Notification nonCompleteRegistration = Notification.show("Max Tickets cannot be greater than Event Capacity", 3000, Notification.Position.BOTTOM_CENTER);
                    nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
                else{
                    event.setEventName(form.eventName.getValue());
                    event.setEventGenres(form.eventGenres.getValue());
                    event.setHost(form.host.getValue());
                    event.setPlace(form.place.getValue());
                    event.setEventTime(form.eventTime.getValue());
                    event.setDateStart(form.dateStart.getValue());
                    event.setDateEnd(form.dateEnd.getValue());
                    event.setEventCapacity(form.eventCapacity.getValue());
                    event.setEventInfo(form.eventInfo.getValue());
                    event.setMaxTickets(form.maxTickets.getValue());
                    event.setAvailableTickets(form.maxTickets.getValue());
                    eventService.saveEvent(event);
                    closeEditor();
                    removeAll();
                    setSizeFull();
                    configureGrid();
                    configureForm(principal);
                    add(
                            getToolbar(principal),
                            getContent()
                    );
                    updateList(principal);
                    closeEditor();
                }
            });
            form.delete.addClickListener(buttonClickEvent -> {
                closeEditor();
                eventService.deleteEvent(event);
                removeAll();
                setSizeFull();
                configureGrid();
                configureForm(principal);
                add(
                        getToolbar(principal),
                        getContent()
                );
                updateList(principal);
                closeEditor();
            });
            form.cancel.addClickListener(buttonClickEvent -> {
                closeEditor();
                removeAll();
                setSizeFull();
                configureGrid();
                configureForm(principal);
                add(
                        getToolbar(principal),
                        getContent()
                );
                updateList(principal);
                closeEditor();
            });
            form.setVisible(true);
        }
    }
    private List<String> getHostName() {
        List<Host> hosts = hostService.findAllHosts();
        List<String> temp = new ArrayList<>();
        for(int i = 0; i < hosts.size(); i++) {
            temp.add(hosts.get(i).getHostName());
        }
        return temp;
    }
    private void createEvent(Event event) {
        if(event == null){
            closeEditor();
        } else{
            form.setVisible(true);
            addClassName("editing");
        }
    }
}