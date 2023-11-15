package thelocalspot.application.views.list.host;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Host-Welcome")
@Route(value = "host-welcome", layout = HostMainLayout.class)
@PermitAll
public class HostWelcome extends VerticalLayout {
    public HostWelcome() {

        H1 welcome = new H1("Welcome Host");
        add(welcome);
    }
}