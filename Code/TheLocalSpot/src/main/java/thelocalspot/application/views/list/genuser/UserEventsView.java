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
import thelocalspot.application.data.entity.Event;
import thelocalspot.application.data.service.EventService;

@PageTitle("General User")
@Route(value = "user-events", layout = UserMainLayout.class)
@PermitAll
public class UserEventsView extends VerticalLayout {

    Grid<Event> genUserEventGrid = new Grid<>(Event.class);
    TextField filterText = new TextField();
    EventService eventService;
    TicketPurchaseForm ticketPurchaseForm;

    public UserEventsView(EventService eventService) {
        this.eventService = eventService;
        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private void configureGrid() {

        genUserEventGrid.setClassName("gen-user-event-grid");
        genUserEventGrid.setSizeFull();
        genUserEventGrid.setColumns("eventType", "eventName", "eventTime", "dateStart", "dateEnd", "eventInfo", "availableTickets", "ticketPrice");
        genUserEventGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        genUserEventGrid.asSingleSelect().addValueChangeListener(gridEventComponentValueChangeEvent -> {
            purchaseEvent(gridEventComponentValueChangeEvent.getValue());
        });
    }

    private void purchaseEvent(Event event) {

        if(event == null){
            closeEditor();
        }else {
            ticketPurchaseForm.eventType.setValue(event.getEventType());
            ticketPurchaseForm.eventName.setValue(event.getEventName());
            ticketPurchaseForm.eventTime.setValue(event.getEventTime().toString());
            ticketPurchaseForm.dateStart.setValue(event.getDateStart().toString());
            ticketPurchaseForm.dateEnd.setValue(event.getDateEnd().toString());
            ticketPurchaseForm.eventInfo.setValue(event.getEventInfo());
            ticketPurchaseForm.ticketPrice.setValue("$" + event.getTicketPrice().toString());
            ticketPurchaseForm.setVisible(true);
            ticketPurchaseForm.purchaseTicket.addClickListener(buttonClickEvent -> {
                Notification.show("Please enter in all the fields for registration", 3000, Notification.Position.BOTTOM_CENTER);
            });
            ticketPurchaseForm.cancel.addClickListener(buttonClickEvent -> {
                closeEditor();
            });
        }

    }

    private void configureForm() {

        ticketPurchaseForm = new TicketPurchaseForm();
        ticketPurchaseForm.setWidth("25em");
    }

    private HorizontalLayout getToolbar() {

        filterText.setPlaceholder("Filter by Event Type...");
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

        genUserEventGrid.setItems(eventService.getEventDetails());
    }

    private void closeEditor() {

        ticketPurchaseForm.setVisible(false);
    }
}
