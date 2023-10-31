package thelocalspot.application.views.list.genuser;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.cglib.core.Local;
import thelocalspot.application.data.entity.CoordUser;
import thelocalspot.application.data.entity.Event;
import thelocalspot.application.data.entity.Ticket;
import thelocalspot.application.data.service.CoordUserService;
import thelocalspot.application.data.service.EventService;
import thelocalspot.application.data.service.TicketService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@PageTitle("General User")
@Route(value = "user-events", layout = UserMainLayout.class)
@PermitAll
public class UserEventsView extends VerticalLayout {
    Grid<Event> genUserEventGrid;
    TextField filterText = new TextField();
    EventService eventService;
    TicketService ticketService;
    CoordUserService coordUserService;
    TicketPurchaseForm ticketPurchaseForm;
    public UserEventsView(EventService eventService, TicketService ticketService, CoordUserService coordUserService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.coordUserService = coordUserService;
        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }
    private void configureGrid() {
        genUserEventGrid = new Grid<>(Event.class);
        genUserEventGrid.setClassName("gen-user-event-grid");
        genUserEventGrid.setSizeFull();
        genUserEventGrid.setColumns("eventGenres", "eventName", "eventTime", "dateStart", "dateEnd", "eventInfo", "availableTickets", "ticketPrice");
        genUserEventGrid.addColumn(event -> event.getCoordUser().getCoordName()).setHeader("Coordinator Name");
        genUserEventGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        genUserEventGrid.asSingleSelect().addValueChangeListener(gridEventComponentValueChangeEvent -> {
            purchaseEvent(gridEventComponentValueChangeEvent.getValue());
        });
    }
    private void configureForm() {
        ticketPurchaseForm = new TicketPurchaseForm();
        ticketPurchaseForm.setWidth("25em");
    }
    private void purchaseEvent(Event event) {
        if(event == null){
            closeEditor();
        }else {
            genUserEventGrid.asSingleSelect().clear();
            ticketPurchaseForm.eventGenre.setValue(event.getEventGenres());
            ticketPurchaseForm.eventName.setValue(event.getEventName());
            ticketPurchaseForm.eventTime.setValue(event.getEventTime().toString());
            ticketPurchaseForm.dateStart.setValue(event.getDateStart().toString());
            ticketPurchaseForm.dateEnd.setValue(event.getDateEnd().toString());
            ticketPurchaseForm.eventInfo.setValue(event.getEventInfo());
            ticketPurchaseForm.ticketPrice.setValue("$" + event.getTicketPrice().toString());
            ticketPurchaseForm.setVisible(true);
            ticketPurchaseForm.purchaseTicket.addClickListener(buttonClickEvent -> {
                ticketService.saveTicket(new Ticket(event.getId(), event.getCoordUser().getId().toString(), LocalDate.now().toString(), true));
                int availableTickets = Integer.parseInt(event.getAvailableTickets());
                availableTickets = availableTickets - 1;
                event.setAvailableTickets(Integer.toString(availableTickets));
                genUserEventGrid.asSingleSelect().clear();
                closeEditor();
                removeAll();
                setSizeFull();
                configureGrid();
                configureForm();
                add(getToolbar(), getContent());
                updateList();
                closeEditor();
                genUserEventGrid.asSingleSelect().clear();
            });
            ticketPurchaseForm.cancel.addClickListener(buttonClickEvent -> {
                closeEditor();
            });
        }
    }
    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by Event Genre...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        var toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("gen-user-grid-toolbar");
        return toolbar;
    }
    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(genUserEventGrid, ticketPurchaseForm);
        content.setFlexGrow(2, genUserEventGrid);
        content.setFlexGrow(1, ticketPurchaseForm);
        content.setSizeFull();
        return content;
    }
    private void updateList() {
        genUserEventGrid.setItems(eventService.findAllEventsStatusTrue());
    }
    private void closeEditor() {
        genUserEventGrid.asSingleSelect().clear();
        ticketPurchaseForm.setVisible(false);
    }
}