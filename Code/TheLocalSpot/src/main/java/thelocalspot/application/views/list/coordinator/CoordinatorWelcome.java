package thelocalspot.application.views.list.coordinator;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Coordinator-Welcome")
@Route(value = "coordinator-welcome", layout = CoordinatorMainLayout.class)
@PermitAll
public class CoordinatorWelcome extends VerticalLayout {

    public CoordinatorWelcome() {

        H1 welcome = new H1("Welcome Coordinator");
        add(welcome);
    }
}