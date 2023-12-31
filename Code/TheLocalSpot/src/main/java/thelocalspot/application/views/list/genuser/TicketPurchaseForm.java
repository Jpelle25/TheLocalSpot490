package thelocalspot.application.views.list.genuser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("General User")
@Route(value = "ticket-purchase-form", layout = UserMainLayout.class)
@PermitAll
public class TicketPurchaseForm extends FormLayout {

    TextField eventGenre = new TextField("Event Genre");
    TextField eventName = new TextField("Event Name");
    TextField eventTime = new TextField("Event Time");
    TextField dateStart = new TextField("Date Start");
    TextField dateEnd = new TextField("Date End");
    TextArea eventInfo = new TextArea("Event Info");
    TextField ticketPrice = new TextField("Ticket Price");
    Button purchaseTicket = new Button("Purchase Ticket");
    Button cancel = new Button("Cancel");

    public TicketPurchaseForm() {
        addClassName("ticket-purchase-Form");
        eventGenre.setReadOnly(true);
        eventName.setReadOnly(true);
        eventTime.setReadOnly(true);
        dateStart.setReadOnly(true);
        dateEnd.setReadOnly(true);
        eventInfo.setReadOnly(true);
        ticketPrice.setReadOnly(true);
        add(
                eventGenre,
                eventName,
                eventTime,
                dateStart,
                dateEnd,
                eventInfo,
                ticketPrice,
                purchaseTicket,
                cancel
        );
    }
}
