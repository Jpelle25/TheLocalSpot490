package thelocalspot.application.views.list.coordinator;
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
import java.util.ArrayList;
import java.util.List;
@PageTitle("Coordinator")
@Route(value = "coordinator-events", layout = CoordinatorMainLayout.class)
@PermitAll
public class CoordinatorEventsView extends VerticalLayout {
    private final CoordUserService coordUserService;
    private final HostService hostService;
    private final EventService eventService;
    private final PlaceService placeService;
    Grid<Event> grid;
    TextField filterText = new TextField();
    EventForm form;
    public CoordinatorEventsView(CoordUserService coordUserService, HostService hostService, EventService eventService, PlaceService placeService) {
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
// coordUsers.get(0).getId().toString()
            List<Event> events = eventService.findAllEventsById(coordUsers.get(0));
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
        form.finalize.addClickListener(buttonClickEvent -> {
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
                Notification nonCompleteRegistration = Notification.show("Please enter in all the fields for registration", 3000, Notification.Position.BOTTOM_CENTER);
                nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else {
                List<CoordUser> coordUserSession = coordUserService.getCoordUserEmail(principal.getAttribute("email"));
                Long coordUserSessionID = coordUserSession.get(0).getId();
                eventService.saveEvent(new Event(form.eventName.getValue(), coordUserSession.get(0), form.host.getValue(), form.place.getValue(),
                        false, form.eventGenres.getSelectedItems(), form.eventTime.getValue(), form.dateStart.getValue(),
                        form.dateEnd.getValue(), form.eventCapacity.getValue(), form.eventInfo.getValue(), form.maxTickets.getValue(), Float.valueOf(form.ticketPrice.getValue())));
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
    }

    private Component getToolbar(OAuth2AuthenticatedPrincipal principal) {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList(principal));
        Button addContactButton = new Button("Add Event");
        addContactButton.addClickListener(e -> addEvent());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    private void addEvent() {
        grid.asSingleSelect().clear();
        editEvent(new Event());
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
            createEvent(gridEventComponentValueChangeEvent.getValue());
        });
    }
    private void createEvent(Event event) {
        if(event == null) {
            closeEditor();
        } else {
            form.eventName.setValue(event.getEventName());
// form.host.setValue(event.getHost().getHostName());
            form.host.setItems(hostService.findAllHosts());
            form.place.setItems(placeService.findAllPlaces());
            form.eventTime.setValue(event.getEventTime());
            form.dateStart.setValue(event.getDateStart());
            form.dateEnd.setValue(event.getDateEnd());
            form.eventCapacity.setValue(event.getEventCapacity());
            form.eventInfo.setValue(event.getEventInfo());
            form.maxTickets.setValue(event.getMaxTickets());
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
    private void editEvent(Event event) {
        if(event == null){
            closeEditor();
        } else{
            form.setVisible(true);
            addClassName("editing");
        }
    }
}