package thelocalspot.application.views.list.coordinator;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Coordinator")
@Route(value = "coordinator-events", layout = CoordinatorMainLayout.class)
@PermitAll
public class CoordinatorEventsView extends VerticalLayout {

    public CoordinatorEventsView() {
        setSpacing(false);
        add(new H2("This place intentionally left empty"));
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }
}
