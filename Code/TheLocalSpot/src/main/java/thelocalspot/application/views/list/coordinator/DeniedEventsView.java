package thelocalspot.application.views.list.coordinator;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
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
import thelocalspot.application.data.service.CoordUserService;
import thelocalspot.application.data.service.EventService;
import thelocalspot.application.data.service.HostService;
import thelocalspot.application.data.service.PlaceService;

import java.util.List;

@PageTitle("Coordinator")
@Route(value = "coordinator-denied-events", layout = CoordinatorMainLayout.class)
@PermitAll
public class DeniedEventsView extends VerticalLayout {
    private final CoordUserService coordUserService;
    private final EventService eventService;
    Grid<Event> grid;

    public DeniedEventsView(CoordUserService coordUserService, EventService eventService) {
        this.coordUserService = coordUserService;
        this.eventService = eventService;
        setSizeFull();
        configureGrid();
        add(
                getContent()
        );
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        updateList(principal);
    }

    private void configureGrid() {
        grid = new Grid<>(Event.class);
        grid.addClassName("event-grid");
        grid.setSizeFull();
        grid.setColumns("eventName", "eventGenres", "eventTime", "dateStart", "dateEnd", "eventCapacity", "eventInfo", "maxTickets", "availableTickets", "ticketPrice", "feedback");
        grid.addColumn(event -> event.getHost().getHostName()).setHeader("Host");
        grid.addColumn(event -> event.getPlace().getPlaceName()).setHeader("Place");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void updateList(OAuth2AuthenticatedPrincipal principal) {
        List<CoordUser> coordUsers = coordUserService.getCoordUserEmail(principal.getAttribute("email"));
        if(!coordUsers.isEmpty()) {

            List<Event> events = eventService.findAllEventsByIdAndDeny(coordUsers.get(0));
            grid.setItems(events);
        }
    }
}