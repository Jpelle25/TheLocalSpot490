package thelocalspot.application.views.list.host;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Host")
@Route(value = "host-events", layout = HostMainLayout.class)
@PermitAll
public class HostEventsView extends VerticalLayout {

    public HostEventsView() {
        setSpacing(false);
        add(new H2("This place intentionally left empty"));
        add(new Paragraph("It’s a place where you can grow your host events page"));
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }
}
