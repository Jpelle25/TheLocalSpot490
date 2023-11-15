package thelocalspot.application.views.list.genuser;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
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
import thelocalspot.application.data.entity.Event;
import thelocalspot.application.data.entity.GenUser;
import thelocalspot.application.data.entity.Ticket;
import thelocalspot.application.data.service.TicketService;
import thelocalspot.application.data.service.GenUserService;

import java.time.LocalDate;
import java.util.List;

@PageTitle("General User")
@Route(value = "Tickets", layout = UserMainLayout.class)
@PermitAll
public class TicketsView extends VerticalLayout {
    GenUserService genUserService;

    TicketService ticketService;
    Button transferTicket;
    Button purchaseHistory;
    Grid<Ticket> ticketHistory;
    TransferTicketForm transferTicketForm;

    public TicketsView(GenUserService genUserService, TicketService ticketService) {
        this.genUserService = genUserService;
        this.ticketService = ticketService;
        resetView();
    }

    private void resetView() {

        //Transfer Ticket Inner View
        transferTicket = new Button("Transfer Ticket");
        transferTicket.addClickListener(buttonClickEvent -> {
            transferTicketView();
        });
        //Purchase History Inner View
        purchaseHistory = new Button("Purchase History");
        purchaseHistory.addClickListener(buttonClickEvent -> {
            purchaseHistoryView();
        });
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(transferTicket, purchaseHistory);
        add(hl);
        hl.setPadding(true);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    public void purchaseHistoryView(){

        removeAll();
        transferTicket = new Button("Transfer Ticket");
        transferTicket.addClickListener(buttonClickEvent -> {
            transferTicketView();
        });
        //Purchase History Inner View
        purchaseHistory = new Button("Purchase History");
        purchaseHistory.addClickListener(buttonClickEvent -> {
            purchaseHistoryView();
        });
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(transferTicket, purchaseHistory);
        add(hl);
        hl.setPadding(true);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        List<GenUser> selfUser = genUserService.findGenUser(principal.getAttribute("email"));
        List<Ticket> ticketsSelfOwn = ticketService.findALlTicketsSelfOwn(selfUser.get(0));
        ticketHistory = new Grid<>(Ticket.class);
        ticketHistory.setClassName("ticket-history");
        ticketHistory.setSizeFull();
        ticketHistory.setColumns("id", "purchaseDate");
        ticketHistory.addColumn(event -> event.getGenUser().getId()).setHeader("Gen User ID");
        ticketHistory.addColumn(event -> event.getGenUser().getFirstName()).setHeader("First Name");
        ticketHistory.addColumn(event -> event.getGenUser().getLastName()).setHeader("Last Name");
        ticketHistory.addColumn(event -> event.getGenUser().getEmail()).setHeader("Email");
        ticketHistory.addColumn(event -> event.getEvent().getEventName()).setHeader("Event Name");
        ticketHistory.addColumn(event -> event.getEvent().getDateStart()).setHeader("Event Start Date");
        ticketHistory.getColumns().forEach(col -> col.setAutoWidth(true));
        ticketHistory.setItems(ticketsSelfOwn);
        add(ticketHistory);

    }

    public void transferTicketView() {

        removeAll();
        transferTicket = new Button("Transfer Ticket");
        transferTicket.addClickListener(buttonClickEvent -> {
            transferTicketView();
        });
        //Purchase History Inner View
        purchaseHistory = new Button("Purchase History");
        purchaseHistory.addClickListener(buttonClickEvent -> {
            purchaseHistoryView();
        });
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(transferTicket, purchaseHistory);
        add(hl);
        add(new H2("Please Click on Ticket and Select User to Transfer Ticket"));
        hl.setPadding(true);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        List<GenUser> selfUser = genUserService.findGenUser(principal.getAttribute("email"));
        List<Ticket> ticketsSelfOwn = ticketService.findALlTicketsSelfOwn(selfUser.get(0));
        ticketHistory = new Grid<>(Ticket.class);
        ticketHistory.setClassName("ticket-history");
        ticketHistory.setSizeFull();
        ticketHistory.setColumns("id", "purchaseDate");
        ticketHistory.addColumn(event -> event.getGenUser().getId()).setHeader("Gen User ID");
        ticketHistory.addColumn(event -> event.getGenUser().getFirstName()).setHeader("First Name");
        ticketHistory.addColumn(event -> event.getGenUser().getLastName()).setHeader("Last Name");
        ticketHistory.addColumn(event -> event.getGenUser().getEmail()).setHeader("Email");
        ticketHistory.addColumn(event -> event.getEvent().getEventName()).setHeader("Event Name");
        ticketHistory.addColumn(event -> event.getEvent().getDateStart()).setHeader("Event Start Date");
        ticketHistory.getColumns().forEach(col -> col.setAutoWidth(true));
        ticketHistory.setItems(ticketsSelfOwn);
        transferTicketForm = new TransferTicketForm(genUserService);
        transferTicketForm.setWidth("25em");
        HorizontalLayout content = new HorizontalLayout(ticketHistory, transferTicketForm);
        content.setFlexGrow(2, ticketHistory);
        content.setFlexGrow(1, transferTicketForm);
        content.setSizeFull();
        add(content);
        ticketHistory.asSingleSelect().addValueChangeListener(gridTicketComponentValueChangeEvent -> {
            transferTicketForm.transfer.addClickListener(buttonClickEvent -> {
                if (transferTicketForm.genUsers.isEmpty()){
                    Notification nonCompleteRegistration = Notification.show("Please select a user to transfer to or cancel", 3000, Notification.Position.BOTTOM_CENTER);
                    nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
                else {
                    gridTicketComponentValueChangeEvent.getValue().setGenUser(transferTicketForm.genUsers.getValue());
                    ticketService.saveTicket(gridTicketComponentValueChangeEvent.getValue());
                    ticketHistory.asSingleSelect().clear();
                    removeAll();
                    transferTicketView();
                }
            });
            transferTicketForm.cancel.addClickListener(buttonClickEvent -> {
                transferTicketView();
            });
        });
        ticketHistory.asSingleSelect().clear();
    }
}
